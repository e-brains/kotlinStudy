

fun main() {

    println("==== 게시판 관리 프로그램 시작 ====")

    // 가장 마지막에 입력된 게시물 번호
    var articlesLastId = 0
    while (true){

        print("명령어 ) ")
        var command = readLine()!!.trim()
        println("입력한 명령어 : $command")

        //if (command == "system exit"){
        if (command == "q"){
            println("프로그램을 종료합니다.")
            break

        //}else if(command == "article write"){
        }else if(command == "w"){

            var id = ++articlesLastId
            print("제목 : ")
            var title = readLine()!!.trim()
            print("내용 : ")
            var body = readLine()!!.trim()

            val article = Article(id, title, body)

            println("$id 번 게시물이 작성되었습니다.")

            // 마지막에 게시물이 추가되면
            articlesLastId = id

        }
    }


    println("==== 게시판 관리 프로그램 끝  ====")

}

data class Article( val id : Int , val title : String , val body : String)
