import java.text.SimpleDateFormat

val articles = mutableListOf<Article>() // 작성글 저장소
var articlesLastId = 0 // 가장 마지막에 입력된 게시물 번호

fun main() {

    println("==== 게시판 관리 프로그램 시작 ====")

    // 페이지 처리를 우한 테스트 데이터 만들기
    makeTestArticles()

    loop@while (true) {

        print("명령어 ) ")
        var command = readLine()!!.trim()
        println("입력한 명령어 : $command")

        when {
            // 프로그램 종료
            command == "q" -> {
                println("프로그램을 종료합니다.")
                break@loop
            }

            // 글 작성
            command == "wr" -> {

                print("제목 : ")
                var title = readLine()!!.trim()
                print("내용 : ")
                var body = readLine()!!.trim()

                val id = addArticle(title, body) // 글 작성

                println("$id 번 게시물이 작성되었습니다.")

            }

            // 글 목록 조회 ( 정렬 기능 및 페이지 추가 )
            command == "li" -> {

                print("요청 페이지 번호 : ")
                val page = readLine()!!.trim().toInt()

                val searchKeyWord = ""
                val itemCountInPage = 10  // 페이지당 보여 줄 게시글 수
                val offSetCount = (page - 1) * itemCountInPage  // 요청페이지 직전 페이지까지의 아이템수 (건너뛸 수)

                val articles = getArticles(searchKeyWord, offSetCount, itemCountInPage)



                println("번호 /       작성날짜 /        수정날짜          / 제목 ")
//                articles.withIndex().reversed().forEach {
//                    println("${it.value.id} / ${it.value.regDate} / ${it.value.updateDate} / ${it.value.title}")
//                }
                // getArticles에서 정순 처리하면 여기서 reversed사용해서 역순으로 만들어줌
//                for (article in articles.reversed()) {
//                    println("${article.id} / ${article.regDate} / ${article.updateDate} / ${article.title}")
//                }
                // getArticles에서 역순 처리하면 여기서 reversed 할 필요 없음
                for (article in articles) {
                    println("${article.id} / ${article.regDate} / ${article.updateDate} / ${article.title}")
                }
            }

            // 글 삭제
            command == "del" -> {
                print(" 삭제할 게시물 id를 입력해 주세요 : ")
                val id = readLine()!!.trim().toInt()

                // 해당 게시물 존재 여부 확인
                var articleToDelete: Article? = getArticleById(id)

                if (articleToDelete == null) {
                    println("$id 번 게시물은 존재하지 않습니다.")
                    continue // 자신이 속한 반복문의 첫줄로 이동
                }

                articles.remove(articleToDelete)

                println("$id 번 게시물을 삭제합니다.")

            }

            // 글 수정
            command == "mo" -> {
                print(" 수정할 게시물 id를 입력해 주세요 : ")
                val id = readLine()!!.trim().toInt()

                // 해당 게시물 존재 여부 확인
                var articleToModify : Article? = getArticleById(id)

                if (articleToModify == null) {
                    println("$id 번 게시물은 존재하지 않습니다.")
                    continue // 자신이 속한 반복문의 첫줄로 이동
                }

                print("${id}번 새 제목 : ")
                articleToModify.title = readLine()!!.trim()
                print("${id}번 새 내용 : ")
                articleToModify.body = readLine()!!.trim()

                articleToModify.updateDate = Util.getNowDateStr()

                println("$id 번 게시물을 수정합니다.")

            }

            // 글 상세보기
            command == "dt" -> {
                print(" 상세보기 할 게시물 id를 입력해 주세요 : ")
                val id = readLine()!!.trim().toInt()

                // 해당 게시물 존재 여부 확인
                var articleToDetail : Article? = getArticleById(id)

                if (articleToDetail == null) {
                    println("$id 번 게시물은 존재하지 않습니다.")
                    continue // 자신이 속한 반복문의 첫줄로 이동
                }

                println("===========================================")
                println("번호 : ${articleToDetail.id} ")
                println("작성날짜 : ${articleToDetail.regDate} ")
                println("갱신날짜 : ${articleToDetail.updateDate}")
                println("제목 : ${articleToDetail.title}")
                println("상세내용 :  ${articleToDetail.body}")
                println("===========================================")

                println("$id 번 게시물 상세보기를 합니다.")

            }

            // 검색기능
            command == "sc" -> {

                print("검색어 입력 : ")
                val searchKeyWord = readLine()!!.trim()

                var page = 1
                val itemCountInPage = 10  // 페이지당 보여 줄 게시글 수
                val offSetCount = (page - 1) * itemCountInPage  // 요청페이지 직전 페이지까지의 아이템수 (건너뛸 수)


                val articles = getArticles(searchKeyWord, offSetCount, itemCountInPage)



                println("번호 /       작성날짜 /        수정날짜          / 제목 ")
//                articles.withIndex().reversed().forEach {
//                    println("${it.value.id} / ${it.value.regDate} / ${it.value.updateDate} / ${it.value.title}")
//                }
                // getArticles에서 정순 처리하면 여기서 reversed사용해서 역순으로 만들어줌
//                for (article in articles.reversed()) {
//                    println("${article.id} / ${article.regDate} / ${article.updateDate} / ${article.title}")
//                }
                // getArticles에서 역순 처리하면 여기서 reversed 할 필요 없음
                for (article in articles) {
                    println("${article.id} / ${article.regDate} / ${article.updateDate} / ${article.title}")
                }
            }



            else -> {
                println("$command 는 존재하지 않는 명령어입니다.")
            }
        }
    }

    println("==== 게시판 관리 프로그램 끝  ====")

}

// 페이징 처리 함수
fun getArticles(searchKeyWord: String, offSetCount: Int, itemCountInPage: Int): List<Article> {

    val startIndex = articles.lastIndex - offSetCount
    var endIndex = (startIndex - itemCountInPage) + 1

    // 마지막 인덱스가 0 보다 작으면 에러 방지를 위해 0으로 셋팅
    if (endIndex < 0) endIndex = 0
    var filterArticles = articles

    // 검색어가 있는 경우
    if (searchKeyWord.isNotBlank()){

        // 검색어로 요청된 게시글을 담을 임시 버퍼
        val filterKeyWordArticles = mutableListOf<Article>()
        for (article in articles){
            if (article.title.contains(searchKeyWord)){
                filterKeyWordArticles.add(article)
            }
        }
    }

        // 역순으로 정렬된 게시글을 담을 임시 버퍼
        val filterArticles = mutableListOf<Article>()

        for (i in startIndex downTo endIndex){  // 여기서 역순이 되면 호출한 곳에서 reverse할 필요 없음
//    for (i in endIndex .. startIndex){  // 정순이기 때문에 호출한 곳에서 reverse해서 역순을 만들어 줌
            filterArticles.add(articles[i])
        }

        return filterArticles


}


// 테스트 데이터를 만들기 위한 함수
fun makeTestArticles(){
    for (id in 1..25){
        val title = "제목 _${id}"
        val body = "내용==========${id}"

        addArticle(title, body)
    }
}



// 글 작성
fun addArticle(title: String, body: String) : Int {
    var id = ++articlesLastId
    val regDate = Util.getNowDateStr()
    val updateDate = Util.getNowDateStr()

    val article = Article(id, regDate, updateDate, title, body)
    articles.add(article)  // 작성글 저장
    articlesLastId = id

    return id
}



// 해당 게시물 존재 여부 확인
fun getArticleById(id : Int) : Article?{

    for (article in articles) {

        if (article.id == id) {
            return article
        }
    }
    return null
}

data class Article(
    val id: Int,
    val regDate: String,
    var updateDate: String,
    var title: String,
    var body: String
)

// 다른 프로젝트에서 재사용 하기 위해 Util로 묶어둔다.
object Util {

    // 현재 시간 가져오기
    fun getNowDateStr(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(System.currentTimeMillis())
    }
}
