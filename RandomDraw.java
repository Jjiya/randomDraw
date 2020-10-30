package randomDraw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/draw")
public class RandomDraw extends HttpServlet {
   
   public void init(ServletConfig config) throws ServletException {
      System.out.println("init 정상 실행");
   }
   
   protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      PrintWriter out = response.getWriter();
      //값 가져오기
      String[] peopleList = request.getParameterValues("peopleList");
      int peopleResult = Integer.parseInt(request.getParameter("peopleResult"));
      
      for(int i = 0; i < peopleList.length; i++) {
         int mix = (int)(Math.random()*peopleList.length);
         String tmp = peopleList[i];
         peopleList[i] = peopleList[mix];
         peopleList[mix] = tmp;         
      }
      System.out.println(Arrays.toString(peopleList));
      
      String[] pick = new String[peopleResult];
      for(int i=0; i<pick.length; i++) {
         pick[i] = peopleList[(int)(Math.random()*peopleList.length)];
         for(int j=0;j<i;j++) //중복제거를 위한 for문 
            {
                /*현재 a[]에 저장된 랜덤숫자와 이전에 a[]에 들어간 숫자 비교
                 ※예를 들어
                 배열 a[3]에 숫자 6이 들어갔을때 이전에 완성된 배열 a[0],a[1],a[2]와 비교하여
                 숫자 6이 중복되지 않을시 다음 a[4]으로 넘어가고, 중복된다면 다시 a[3]에 중복되지   
                 않는 숫자를 넣기 위하여 i를 한번 감소한후 처음 for문으로 돌아가 다시 a[3]을 채운다
                 */
                if(pick[i]==pick[j])  
                {
                    i--;
                }
            }
//출처: https://itpangpang.xyz/50 [ITPangPang]
      }
      System.out.println(Arrays.toString(pick));   
      
      out.print("<html> <title>랜덤뽑기 결과</title> <body>");
      out.print("<p>당첨자는"+Arrays.toString(pick)+"입니다.</p>");
      out.print("축하드립니다!!!<br>");
      out.print("<a href='http://localhost:8090/WebShop/ramdomDraw/RandomDraw.html'>돌아가기</a>");
      
   }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doHandle(request, response);
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doHandle(request, response);
   }

   public void destroy() {
      System.out.println("destroy 정상 실행");
   }
}