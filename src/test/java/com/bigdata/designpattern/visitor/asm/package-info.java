package com.bigdata.designpattern.visitor.asm;

/**
 * ASM （Assembly Lanuage 作为汇编语言）字节码编辑类库使用
 * 官网指导手册：https://asm.ow2.io/asm4-guide.pdf
 *
 * java class file 包含内容
 * Magic：存放java类文件魔数(magic number 前4个字节) 和版本信息，java文件都是以 Oxcafebabe 开头，这样保证了Java虚拟机能很轻松分辨java字节码文件，和非java文件；
 * Version: 存放Java类版本信息， 具有重要意义，因为随着Java技术不断迭代，类文件格式也在不断变化，类文件的版本信息可以知道虚拟机如何去读取并处理类文件；
 * Constant Pool：存放类的文字字符串、类名、方法名、接口名，final 变量以及外部类的引用信息等常量，其在Java的动态链接中起到核心作用，平均大小占类文件60%左右；
 * Assess Flag：指定该文件中定义的是一个类、还是一个接口（一个class文件只能对应一个类或接口），同时指定了类或接口的访标记，如：public private abstract
 * This Class：指向类的全限定名称字符串常量的指针；
 * Super Class：指向类的父类的全限定名称字符串常量指针；
 * Interfaces：一个指针数组，存放给类或父类的所有实现接口的全限定名称的字符串常量指针。以上三项指向都是常量，特别是前两项，在使用ASM从已有类派生新类时，一般需要修改：
 *      已有类作为新类的父类，新类作为已有类的子类，同时还可以增加新接口实现；
 * Fields：对类或接口中声明的字段进行了细致描述。fields列表中仅列出本类或接口中的字段，不包括从超类和父类中直接继承的字段；
 * Methods：对类或接口中声明的方法进行细致描述。如：方法的名称、参数和返回值类型等，只包含本类或接口的方法，不包括从超类和父类继承的方法，使用ASM进行AOP编程，通常都是通过调整Method中的指令来实现的；
 * Class Attributes：存放了类或接口所定义属性的基本信息 （getter/setter）
 *
 *
 *
 *
 *
 *
 */