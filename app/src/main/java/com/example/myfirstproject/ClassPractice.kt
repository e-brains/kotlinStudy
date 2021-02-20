package com.example.myfirstproject

//기본적인 클래스 생성
class Human{
    val name = "kim"
    fun eatingCake(){
       println("this is so yummy~~~~")
    }
}

//파라미터가 있는 클래스 생성 (생성자 이용)
//class Human2 constructor(name: String){ //파라미터에서 선언해 버림
//class Human2 constructor(val name: String){ //constructor 생략가능
class Human2 (val name: String){
    // 아래 name가 보라색으로 표시되는 것은 프로퍼티의 변수명이
    // 컨스트럭터의 변수명과 동일하니 컨스트럭터의 변수명 하나로 써라라는 의미
    //val name = name  //파라미터에서 선언해 버림
    fun eatingCake(){
        println("this is so yummy~~~~")
    }
}

//default값이 있는 클래스 생성
class Human3 (val name: String="Anonymous"){ //default값을 주면 파라미터 없는 객체 생성가능
    fun eatingCake(){
        println("this is so yummy~~~~")
    }
}

//인스터스 생성 시 초기화 할 수 있는 클래스 생성
class Human4 (val name: String="Anonymous"){
    init {
        println("객체 생성 시 초기화 작업 !")
    }
    fun eatingCake(){
        println("this is so yummy~~~~")
    }
}

//자바는 파라미터를 달리하는 생성자를 여러개 만들 수 있는데 (오버로딩)
//코틀린은 주 생성자(this)로 부터 위임을 받아서  부 생성자를 만들게 된다.
class Human5 (val name: String="Anonymous"){
    constructor(name:String, age:Int):this(name){
        println("age가 추가된 부생성자 생성 .... age:${age}")
    }
    //아래는 주생성자의 init이기 때문에 위 부constructor내용 보다 먼저 수행됨
    init {
        println("부생성자가 있는 객체 생성 시 초기화 작업 !")
    }
    fun eatingCake(){
        println("this is so yummy~~~~")
    }
}

//클래스 상속
//코클린의 클래스는 기본적으로 final이기 때문에 같은 파일내에 있어도 참조가
// 안되기 때문에 상속 받을려면 open을 해줘야 한다.
//상속은 자바와 동일하게 1개만 가능
open class Human6 (val name: String="Anonymous"){
    constructor(name:String, age:Int):this(name){
        println("age가 추가된 부생성자 생성 .... age:${age}")
    }
    //아래는 주생성자의 init이기 때문에 위 부constructor내용 보다 먼저 수행됨
    init {
        println("부생성자가 있는 객체 생성 시 초기화 작업 !")
    }
    open fun eatingCake(){
        println("this is so yummy~~~~")
    }
}
class Korean : Human6(){
    //Human6클래스의 eatingCake()을 오버라이드해서 사용하려면
    //Human6클래스의 eatingCake()을 open해야 한다.
    override fun eatingCake(){
        println("맛있네 ~~")
        //부모 클래스의 eatingCake()을 같이 사용하고 싶으면 super를 이용
        super.eatingCake()
    }
}

fun main(){
    //기본적인 객체 생성 및 사용
    val human = Human()
    human.eatingCake()
    println("this is human's name is ${human.name}")

    //파라미터가 있는 객체 생성 및 사용
    val human2 = Human2("hong")
    human2.eatingCake()
    println("this is human's name is ${human2.name}")

    //파라미터가 없는 객체 생성 및 사용
    val human3 = Human3()
    human3.eatingCake()
    println("this is human's name is ${human3.name}")

    //초기화가 있는 객체 생성 및 사용
    val human4 = Human4()
    human4.eatingCake()
    println("this is human's name is ${human4.name}")

    //부생성자가 있는 객체 생성 및 사용
    val human5 = Human5("kim", 15)
    human5.eatingCake()
    println("this is human's name is ${human5.name}")

    //상속된 클래스 객체 생성
    val korean = Korean()
    korean.eatingCake()
    println("this is human's name is ${korean.name}")


}

