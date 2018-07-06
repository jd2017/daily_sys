package com.yun.枚举;

/**
 * 枚举类不能派生子类（类的默认修饰符为final)，其原因基于它只有private构造器
 * 其实很容易想明白，所谓枚举类就是有包含有固定数量实例（并且实例的值也固定）的特殊类，
 * 如果其含有public构造器，那么在类的外部就可以通过这个构造器来新建实例，显然这时实例的数量和值就不固定了，
 * 这与定义枚举类的初衷相矛盾，为了避免这种形象，就对枚举类的构造器默认使用private修饰。
 * 如果为枚举类的构造器显式指定其它访问控制符，则会编译出错。
 */
public enum EnumClass {
    //注意枚举类的所有实例必须在其首行显式列出，否则它不能产生实例,实例也可以为小写，不报错，但规范用大写
    SMALL("s"),MEDIUM("m"),LARGE("l"),MAX("max");

    /**
     * 构造器只是在构造枚举常量的时候调用,构造器不能用public和protected修饰
     * @param cname
     */
    private EnumClass(String cname){
        this.cname=cname;
    }
//    EnumClass(String cname, String age){
//        this.cname=cname;
//    }
//    EnumClass(){
//    }

    private String cname ;
    public String getCname(){
        return cname;
    }
}
