package com.example.myfirstproject

fun main(){
//    hello()
//    println(add(3,4))
//    str()
//    checkNum(90)
//    forAndWhile()
    nullCheck()
}

//1.함수
fun hello() : Unit{ // 리턴값이 없는 경우 Unit명시,  생략가능
    println("hello world !")
}

fun add (a:Int, b:Int) : Int {
    return a+b
}

//2.val(=value) va var(=variable)
fun hi(){
    val a : Int = 10
    var b : Int = 9
    var c = 11 // 변수 타입을 추론하기 때문에 타입선언 생략해도 됨
    var name = "abd" //스트링도 생략가능
    var s : String //값 할당없이 선언만 하는 경우는 타입까지 선언
}

//3. String Template ( 문자열에 $를 이용하여 변수 사용 )
fun str(){
    val name = "kim"
    println("my name is $name")
    println("my name is ${name}I'm 123") //이어지는 문장이 뛰어쓰기 하면 안되는 경우 {} 사용
    println("this is 2\$a") //문자로 2$a를 그대로 찍고 싶을때 \역슬래쉬 사용

}

//4. 조건식
fun maxBy(a:Int, b:Int) : Int {
    if (a>b){
        return a
    }else{
        return b
    }
}

//4. 조건식 : 간편식
fun maxBy2(a:Int, b:Int) = if(a>b) a else b
fun checkNum(score:Int) {
    when (score) {
        0 -> println("this is 0")
        1 -> println("this is 1")
        2,3 -> println("this is 2 or 3") // 복수 사용 가능
        else -> println("I don't know")
    }
    //when 을 이용한 리턴식 (이때에는 null 방지를 위해 반드시 else 를 써줘야 한다.)
    //#ex001
    var b = when(score) {
        1 -> 1
        2 -> 2
        else -> 3  //null 방지를 위해 반드시 else  사용
    }

    //범위 연산자 사용하여 점수 처리
    //#ex002
    when(score){
        in 91..100 -> println("best")
        in 81..90 -> println("good")
        in 10..80 -> println("not bad")
        else -> println("bad")
    }
}
//Expression과  Statement의 차이점
//Expression : #ex001처럼 리턴값을 만들어내는 식인 경우
//Statement : #ex002처럼 리턴값이 아닌 단순 분기나 로직

//5. Array and List
//리스트 중에 MutableList는 수정이 가능한 리스트이다.
fun array(){
    val array:Array<Int> = arrayOf(1,2,4)
    val list:List<Int> = listOf(1,2,3)
    val array2 = arrayOf(1,"kim", 3.4f) //여러타입을 쓸 수 있으며 이때 타입은 <Any>로 추론된다.
    val list2 = listOf(1,"kim",11L) //List 도 동일

    //array는 기본적으로 Mutable이어서 사이즈만 아니면 변경 가능
    array[0] = 3 //0번째를 3으로 바꿈

    //리스트는 수정이 불가하지만 MutableList는 수정이 가능하다.
    //MutableList중에 대표적인 것이 arrayList이다.
    val arrayList = arrayListOf<Int>()  //val을 쓸 수 있는 이유는 arrayList의 주소값은 변하지 않고 내부 값만 수정되기 때문이다.
    //arrayList = arrayListOf()하면 다시 주소를 할당할 수 없다라는 오류 발생
    arrayList.add(10) //10값 추가
}

//6. 반복문 For / While
fun forAndWhile(){
    val students:ArrayList<String> = arrayListOf("kim","james","hong","park")
    for ((i,name) in students.withIndex()){
        println("${i+1}번째 학생 : ${name}")
    }

    var sum : Int = 0
    for (i in 1..10 step 2){
        sum += i
        println(i)
    }
    println(sum)

    for (i in 10 downTo 1 ){ //10에서 1까지
        sum += i
        println(i)
    }

    for (i in 1 until 10 ){ //1..10과 다른점은 10을 포함하지 않음
        sum += i
        println(i)
    }

    var index = 0
    while (index < 10){
        println("current index: ${index}")
        index++
    }
}

//7. Nullable and NonNull
fun nullCheck(){
    var name : String = "kim" // null을 할당 할 수 없다.
    var nullName : String? = null //?을 이용하여 null 허용 , 이때는 타입이 명시된다.
    var nameInUpperCase = name.toUpperCase() //name은 nonNull타입이라서 toUpperCaset사용
    // 아래는 nullName이 null허용이라서 ?를 붙이면 nullNameInUpperCase:String?가 추론되어 할당 가능하게 된다.
    var nullNameInUpperCase = nullName?.toUpperCase()

    //?: (값이 없는 경우 default값을 줄수 있다)
    val lastName:String? = null
    //아래는 lastName이 없으면 "NO lastName"라는 default값을 줘서 출력
    val fullName:String = name +" "+ (lastName?: "NO lastName")
    println(fullName)

    //!! (nullable이긴 하지만 내가 null이 안되게 보증 할게 라고 컴파일러에게 알려준는 연산자)
    // 권장하지는 않는다.
    //val a:String? = null
    //val upper:String = a!! // a가 null인데도 !!로 인해 nonNull타입의 변수에 할당 가능

    val email : String? = "aaa@gmail.com"
    email?.let { //이메일이 null이 아니라면 let 블럭 안으로 실행 시켜라
        println("my email is ${email}")
    }
}









