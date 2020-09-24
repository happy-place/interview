package com.bigdata.designpattern.iterator.v3;


public class Main {

    public static void main(String[] args) {
        Collection_ coll = new ArrayList_();
        for(int i=0;i<3;i++){
            coll.add(i);
        }
        test(coll);


        coll = new LinkedList_();
        for(int i=0;i<3;i++){
            coll.add(i);
        }
        test(coll);

    }

  public static void test(Collection_ coll){
      Iterator_ iterator = coll.iterator();
      while(iterator.hasNext()){
          System.out.println(iterator.next());
      }
  }


}
