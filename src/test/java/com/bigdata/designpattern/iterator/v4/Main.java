package com.bigdata.designpattern.iterator.v4;


public class Main {

    public static void main(String[] args) {
        Collection_<Integer> coll = new ArrayList_<>();
        for(int i=0;i<3;i++){
            coll.add(i);
        }
        test(coll);


        coll = new LinkedList_<>();
        for(int i=0;i<3;i++){
            coll.add(i);
        }
        test(coll);

    }

  public static void test(Collection_<Integer> coll){
      Iterator_<Integer> iterator = coll.iterator();
      while(iterator.hasNext()){
          System.out.println(iterator.next());
      }
  }


}
