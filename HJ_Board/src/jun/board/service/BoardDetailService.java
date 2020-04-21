package jun.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jun.board.action.Action;
import jun.board.command.ActionCommand;
import jun.board.dao.BoardDAO;
import jun.board.model.BoardDTO;

public class BoardDetailService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		boardDAO.setReadCountUpdate(num);
		boardDTO = boardDAO.getDetail(num);
		
		if(boardDTO == null)
		{
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("boardDTO", boardDTO);
		ActionCommand actionCommand = new ActionCommand();
		
		actionCommand.setRedirect(false);
		actionCommand.setPath("./board/board_view.jsp");
		
		return actionCommand;
	}

}
