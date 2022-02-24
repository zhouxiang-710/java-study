###### Lambda表达式 详解


使用 Lambda 表达式原因
~~~
Lambda 是一个匿名函数，可以把 Lambda表达式 理解为是一段可以传递的代码 (将代码像数据一样进行传递)。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升
~~~~
Lambda 表达式的基础语法 :
~~~
Java8 中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符，箭头操作符将 Lambda 表达式拆分成两部分 :
左侧 : Lambda 表达式的参数列表
右侧 : Lambda 表达式中所需执行的功能， 即 Lambda 体

语法格式一 : 无参数，无返回值
() -> System.out.println("Hello Lambda!");

语法格式二 : 有一个参数，并且无返回值
(x) -> System.out.println(x)

语法格式三 : 若只有一个参数，小括号可以省略不写
x -> System.out.println(x)

Consumer<String> con = (x) -> System. out .println(x);
con.accept( "啦啦啦，我是卖报的小行家" );

语法格式四 : 有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
Comparator <Integer> com = (x, y) -> {
System.out.println("函数式接口");
return Integer.compare(x, y);
};

语法格式五 : 若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
Comparator <Integer> com = (x, y) -> Integer.compare(x, y);

语法格式六 : Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
(Integer x, Integer y) -> Integer.compare(x, y);
注 : Lambda 表达式中的参数类型都是由编译器推断得出的。 Lambda 表达式中无需指定类型，程序依然可以编译，这是因为 javac 根据程序的上下文，在后台推断出了参数的类型。 Lambda 表达式的类型依赖于上下文环境，是由编译器推断出来的。这就是所谓的 “类型推断”

上联 : 左右遇一括号省
下联 : 左侧推断类型省
横批 : 能省则省
~~~
Lambda 表达式需要 “函数式接口” 的支持
~~~
函数式接口 : 接口中只有一个抽象方法的接口，称为函数式接口，可以通过 Lambda 表达式来创建该接口的对象 (若 Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上进行声明) 可以使用注解 @FunctionalInterface 修饰可以检查是否是函数式接口，同时 javadoc 也会包含一条声明，说明这个接口是一个函数式接口
@FunctionalInterface
public interface MyFun {
Integer getValue(Integer num);
}

函数式接口中使用泛型 :
@FunctionalInterface
public interface MyFun< T > {
T getValue( T t);
}
~~~
从匿名类到 Lambda 的转换
~~~
// 原来的匿名内部类作为参数传递
Comparator<Integer> comparator = new Comparator<Integer>() {
@Override
public int compare(Integer o1, Integer o2) {
return Integer. compare (o1, o2);
}
};
TreeSet<Integer> treeSet = new TreeSet<>(comparator);

// Lambda 表达式作为参数传递
Comparator<Integer> comparator = (o1, o2) -> Integer. compare (o1, o2);
TreeSet<Integer> treeSet = new TreeSet<>(comparator);
~~~
作为参数传递 Lambda 表达式 :
 ~~~
为了将 Lambda 表达式作为参数传递，接收Lambda 表达式的参数类型必须是与该 Lambda 表达式兼容的函数式接口的类型

需求 : 雇员对象如下，有一个包含许多员工信息的对象 employees，要求获取当前公司中员工年龄大于 35 的员工信息
public class Employee {
private String name ;
private int age ;
public Employee() {
}
public Employee(String name, int age ) {
this . name = name;
this . age = age;
}
// ...
@Override
public String toString() {
return "Employee{" +  "name='" + name + ' ' ' +  ", age=" + age +  '}' ;
}
}

最简单的方式是采用 foreach 循环遍历，以下是各种优化方式

优化方式一 : 策略设计模式
@FunctionalInterface
public interface MyPredicate< T > {
boolean test( T t);
}
public class FilterEmployeeByAge implements MyPredicate<Employee> {
@Override
public boolean test(Employee employee) {
return employee.getAge() >= 35 ;
}
}
public List<Employee> filterEmployees3(List<Employee> employees, MyPredicate<Employee> myPredicate) {
List<Employee> emps = new ArrayList<>();
for (Employee employee : employees) {
if (myPredicate.test(employee)) {
emps.add(employee);
}
}
return emps;
}
调用
List<Employee> emps = filterEmployees3( employees , new FilterEmployeeByAge());
for (Employee employee : emps) {
System. out .println(employee.toString());
}

优化方式二 : 匿名内部类
List<Employee> emps = filterEmployees3( employees , new MyPredicate<Employee>() {
@Override
public boolean test(Employee employee) {
return employee.getAge() >= 35 ;
}
});
for (Employee employee : emps) {
System. out .println(employee.toString());
}

优化方式三 : Lambda
filterEmployees3( employees , employee -> employee.getAge() >= 35 ).forEach(System. out ::println);

优化方式四 : Stream API
employees .stream().filter((employee) -> employee.getAge() >= 35 ).limit( 5 ).forEach(System. out ::println);
~~~

Java8 内置的四大核心函数式接口
~~~
-----------------------------------------------------------------------------------
函数式接口      | 参数类型  |    返回类型  |   用途
Consumer<T>    |          |             |
消费型接口      |   T      |     void    | 对类型为T的对象应用操作，包含方法 :void accept(T t)
-----------------------------------------------------------------------------------
Supplier<T>    |          |             |
供给型接口      |   无     |       T     | 返回类型为T的对象，包含方法 :T get()
-----------------------------------------------------------------------------------
Function<T, R> |          |
函数型接口      |   T      |      R      | 对类型为T的对象应用操作，并返回结果。结果是R类型的对象，包含方法 :
R apply(T t)   |          |             |
-----------------------------------------------------------------------------------
Predicate<T>   |          |             |
断定型接口      |  T       |    boolean  | 确定类型为T的对象是否满足某约束，并返回boolean 值。包含方法 :boolean test(T t)
-----------------------------------------------------------------------------------

~~~
~~~
Consumer<T> 消费型接口 :
----------
public void happy( double money, Consumer<Double> con) {
con.accept(money);
}
happy( 10000 , (m) -> System. out .println( "吃大餐，每次消费：" + m + "元" ));
----------

Supplier<T> 供给型接口 :
----------
// 需求 : 产生指定个数的整数，并放入集合中
public List<Integer> getNumList( int num, Supplier<Integer> sup) {
List<Integer> list = new ArrayList<>();
for ( int i = 0 ; i < num; i++) {
Integer n = sup.get();
list.add(n);
}
return list;
}
getNumList( 10 , () -> ( int ) (Math. random () * 100 )).forEach(System. out ::println);
----------

Function<T, R> 函数型接口 :
----------
// 需求：用于处理字符串
public String strHandler(String str, Function<String, String> fun) {
return fun.apply(str);
}
String newStr = strHandler( " ttt Good Good Study,Day Day up.   " , (str) -> str.trim());
System. out .println(newStr);
----------

Predicate<T> 断言型接口 :
----------
// 需求：将满足条件的字符串，放入集合中
public List<String> filterStr(List<String> list, Predicate<String> pre) {
List<String> strList = new ArrayList<>();
for (String str : list) {
if (pre.test(str)) {
strList.add(str);
}
}
return strList;
}
List<String> list = Arrays. asList ( "Hello" , "atguigu" , "Lambda" , "www" , "ok" );
filterStr(list, (s) -> s.length() > 3 ).forEach(System. out ::println);
~~~
其他接口(函数式接口)
~~~
函数式接口 BiFunction<T, U, R>
参数类型: T, U
返回类型:R
用途 :  对类型为 T, U 参数应用操作， 返回 R 类型的结果，包含方法为 :R apply(T t, U u)

函数式接口:UnaryOperator<T> (Function子接口)
参数类型:T
返回类型:T
用途 : 对类型为T的对象进行一元运算， 并返回T类型的结果，包含方法为 :T apply(T t) 

函数式接口:BinaryOperator<T> (BiFunction 子接口)T
参数类型:T
返回类型:T
用途 : 对类型为T的对象进行二元运算， 并返回T类型的结果，包含方法为T apply(T t1, T t2)

函数式接口:BiConsumer<T, U>
参数类型:T, U
返回类型:void
用途 : 对类型为T, U 参数应用操作，包含方法为 void accept(T t, U u)

函数式接口:ToIntFunction<T>、ToLongFunction<T>、ToDoubleFunction<T>
参数类型:T
返回类型:int、long、double
用途 :分别计算 int 、 long 、double、 值的函数

函数式接口:IntFunction<R>、LongFunction<R>、DoubleFunction<R>
返回类型:int、long、doubleR
返回类型:R
用途 :参数分别为int、 long、double 类型的函数
~~~

方法引用
~~~
若 Lambda 体中的功能，已经有方法提供实现，可以使用方法引用 (可以将方法引用理解为 Lambda 表达式的另外一种表现形式)
1> 对象的引用 :: 实例方法名
2> 类名 :: 静态方法名
3> 类名 :: 实例方法名
~~~
~~~
注 : 1>  方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致(就是函数签名和返回值一致)
     2> 若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式 : ClassName::MethodName
~~~
~~~
PrintStream ps = System. out ;
Consumer<String> con1 = (str) -> ps .println(str);
Consumer<String> con2 = ps::println;
Consumer<String> con3 = System. out::println;

对象的引用 :: 实例方法名
Employee emp = new Employee( 101 , "张三" , 18 , 9999.99 );
Supplier<String> sup = () -> emp .getName();
System. out .println(sup.get());
Supplier<String> sup2 = emp::getName;
System. out .println(sup2.get());

类名 :: 静态方法名
Comparator<Integer> comparator1 = (x, y) -> Integer. compare (x, y);
Comparator<Integer> comparator2 = Integer:: compare ;

BiFunction<Double, Double, Double> fun = (x, y) -> Math. max (x, y);
System. out .println(fun.apply( 1.5 , 22.2 ));
BiFunction<Double, Double, Double> fun2 = Math:: max ;
System. out .println(fun2.apply( 1.2 , 1.5 ));

类名 :: 实例方法名
BiPredicate<String, String> bp = (x, y) -> x.equals(y);
System. out .println(bp.test( "abcde" , "abcde" ));
BiPredicate<String, String> bp2 = String::equals;
System. out.println(bp2.test( "abc", "abc"));

Function<Employee, String> fun = (e) -> e.show();
System. out .println(fun.apply( new Employee()));
Function<Employee, String> fun2 = Employee::show;
System. out.println(fun2.apply( new Employee()));
~~~
~~~
注 : 当需要引用方法的第一个参数是调用对象，并且第二个参数是需要引用方法的第二个参数 (或无参数) 时 :  ClassName::methodName
~~~
构造器引用
~~~
构造器的参数列表，需要与函数式接口中参数列表保持一致 (就是函数签名一致)
1> 类名 :: new
Supplier<Employee> sup = () -> new Employee();
System. out .println(sup.get());
Supplier<Employee> sup2 = Employee:: new ;

System. out .println(sup2.get());

Function<String, Employee> fun = Employee:: new ;
BiFunction<String, Integer, Employee> fun2 = Employee:: new ;
~~~
数组引用
~~~
1> 类型[] :: new
Function<Integer, String[]> fun = (args) -> new String[args];
String[] strs = fun.apply( 10 );
System. out .println(strs. length );

Function<Integer, Employee[]> fun2 = Employee[]:: new ;
Employee[] emps = fun2.apply( 20 );
System. out .println(emps. length );
~~~