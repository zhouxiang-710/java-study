package InterviewFromXiongJG.test1;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 16:46 2020/11/4
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
class B extends A{
 public B(int i){
     System.out.println("b");
  }
    public B (){
        System.out.println("B");
    }
  public static void main(String[] args) {
    B b=new B (5);
  }
}


//编译通过 输出 A  B