package com.bigdata.designpattern.visitor.asm;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

public class Main {

    /**
     * PrinterVistor 通过ASM访问字节码，扫描了Runnalbe接口的内部细节，并予以打印
     * java/lang/Runnable extends java/lang/Object {
     *  run()V
     * }
     * @throws IOException
     */
    @Test
    public void vistRunnableClass() throws IOException {
        ClassPrinter printerVistor =  new ClassPrinter();
        ClassReader classReader = new ClassReader("java.lang.Runnable"); // 直接从类加载器读取类
        classReader.accept(printerVistor,0);
    }

    /**
     * com/bigdata/designpattern/visitor/asm/TestShowByteCode extends java/lang/Object {
     *  <init>()V
     *  main([Ljava/lang/String;)V
     * }
     * @throws IOException
     */
    @Test
    public void vistMyClassFile() throws IOException {
        ClassPrinter printerVistor =  new ClassPrinter(); // 直接读取磁盘字节码文件
        ClassReader classReader = new ClassReader(Main.class.getClassLoader().getResourceAsStream("com/bigdata/designpattern/visitor/asm/TestShowByteCode.class"));
        classReader.accept(printerVistor,0);
    }

    /**
     * 借助ASM动态生成字节码，使用自定义类加载器加载类，然后尝试获取类的方法
     */
    @Test
    public void writeAClassFile(){
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object", null);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        MyClassLoader myClassLoader = new MyClassLoader();
        Class aClass = myClassLoader.defineClass("pkg.Comparable", b);
        System.out.println(aClass.getMethods()[0].getName());
    }

    /**
     * 模拟AOP对类进行改写
     *
     * classReader -> classVisitor -> classWriter -> bytes -> fos
     *
     */
    @Test
    public void copyAClass() throws IOException {
        ClassReader classReader = new ClassReader(Main.class.getClassLoader().getResourceAsStream("com/bigdata/designpattern/visitor/asm/Car.class"));
        ClassWriter classWriter = new ClassWriter(0);
        ClassVisitor classVisitor = new ClassVisitor(ASM4, classWriter){
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                // 原始 MethodVisitor
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                // 添加AOP逻辑的 MethodVisitor
                MethodVisitor adaptedmv = new MethodVisitor(ASM4,mv){
                    @Override
                    public void visitCode() {
                        if(name.equals("move")){
                            visitMethodInsn(INVOKESTATIC,"LoggingProxy","logging","()V",false);
                        }
                        super.visitCode();
                    }
                };
                // 代替原有mv
                return adaptedmv;
            }
        };

        classReader.accept(classVisitor,0);
        byte[] bytes = classWriter.toByteArray();

        String path = (String)System.getProperties().get("user.dir");
        File dir = new File(path + "/com/bigdata/designpattern/visitor/asm");
        dir.mkdirs();

        String copyPath = dir + "/Car_0.class";
        System.out.println(copyPath);

        FileOutputStream fos = new FileOutputStream(new File(copyPath));
        fos.write(bytes);
        fos.flush();
        fos.close();
    }





}
