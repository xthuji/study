package com.hj.test.study;
public class StringStaticTest {

    public static void setA(String a)
    {
        System.out.println("In the setmethod before set:#### " + a);
        a = "I am a student";
        System.out.println("In the setmethod after set:#### " + a);
    }

    public static void main(String[] args)
    {
        String a = "China is our motherland";
        System.out.println("Out before set:----- "+ a);
        setA(a);
        System.out.println("Out after set:----- "+a);
        /*结果显示
        Out before set:----- China is our motherland
        In the setmethod before set:#### China is our motherland
        In the setmethod after set:#### I am a student
        Out after set:----- China is our motherland */
        
/*      说明：
                        传进来的参数只是值的引用,你要改变某个变量的值,那你得要找到她确切的物理地址,才行
        a = "I am a student";
                        是创建了一个新的对象的

                        如果要实现你说的，改变字符串，那就下面两种方法：
        1。传进来的时候就不要传String，传StringBuffer。因为String是final的
        2。传进来一个对象，你的a,b,c,d,e是对象的属性，然后改变对象的属性，就可以了

                        还有和2类似的，就是传进来数组，然后得到结果后解析数组得到结果，比建立类稍微简单一点点*/
    } 
}
