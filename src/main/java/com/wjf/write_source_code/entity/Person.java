package com.wjf.write_source_code.entity;

/**
 * @ClassName Person
 * @Description todo
 * @Author wjf
 * @Date 2020/11/25 19:11
 * @Version V1.0
 **/
public class Person {

    private Integer age;

    private String name;

    private Double height;

    private String eat(String name){
        String msg=name+"正在吃饭";
        System.out.println(msg);
        return msg;
    }
    public Person() {
    }

    public Person(Integer age, String name, Double height) {
        this.age = age;
        this.name = name;
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

}
