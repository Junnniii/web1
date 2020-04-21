package jun.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jun.board.action.Action;
import jun.board.command.ActionCommand;
import jun.board.service.BoardAddService;
import jun.board.service.BoardDetailService;
import jun.board.service.BoardDownloadService;
import jun.board.service.BoardListService;
import jun.board.service.BoardModifyDetailService;
import jun.board.service.BoardModifyService;
import jun.board.service.BoardReplyMoveService;
import jun.board.service.BoardReplyService;
import jun.board.service.BoardSearchListService;


@WebServlet("/BoardFrontController")
public class BoardFrontController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서블릿 맵핑명을 설정한다.
		String requestURI = request.getRequestURI( );
		String contextPath = request.getContextPath( );
		String pathURL = requestURI.substring(contextPath.length( ));
		//포워딩 정보를 저장한다.
		ActionCommand actionCommand = null;
		//메소드를 규격화한다.
		Action action = null;
		if(pathURL.equals("/BoardList.do")) 
		{
			action = new BoardListService();
			try {
			actionCommand = action.execute(request, response);
			} catch (Exception e) {
			e.printStackTrace( );
			}
		}
		else if(pathURL.equals("/BoardWrite.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./board/board_write.jsp");
		}
		else if(pathURL.equals("/BoardAdd.do")) 
		{
			action = new BoardAddService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(pathURL.equals("/BoardDetail.do")) 
		{
			action = new BoardDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				 e.printStackTrace();
			}
		}
		else if(pathURL.equals("/BoardDownload.do"))
		{
			action = new BoardDownloadService();
			try {
				actionCommand = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(pathURL.equals("BoardReply.do"))
		{
			action = new BoardReplyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				 e.printStackTrace();
			}
		}
		else if(pathURL.equals("/BoardReplyMove.do"))
		{
			action = new BoardReplyMoveService();
			try {
				actionCommand = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(pathURL.equals("/BoardModify.do")) {
			action = new BoardModifyDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(pathURL.equals("/BoardModifyService.do")) {
			action = new BoardModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(pathURL.equals("/BoardDelete.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./board/board_delete.jsp");
		}
		else if(pathURL.equals("/BoardDeleteService.do")) {
			action = new BoardDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(pathURL.equals("/BoardSearchList.do")) {
			action = new BoardSearchListService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		if(actionCommand != null)
		{
			if(actionCommand.isRedirect())
			{
				response.sendRedirect(actionCommand.getPath());
			}
			else
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		service(request, response);
	}

}
