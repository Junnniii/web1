package jun.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jun.board.action.Action;
import jun.board.command.ActionCommand;
import jun.board.dao.BoardDAO;
import jun.board.model.BoardDTO;

public class BoardReplyService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		int result = 0;
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try {
			MultipartRequest multipartRequest = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			boardDTO.setNum(Integer.parseInt(multipartRequest.getParameter("num")));
			boardDTO.setName(multipartRequest.getParameter("name"));
			boardDTO.setPass(multipartRequest.getParameter("pass"));
			boardDTO.setSubject(multipartRequest.getParameter("subject"));
			boardDTO.setContent(multipartRequest.getParameter("content"));
			boardDTO.setAnswer_num(Integer.parseInt(multipartRequest.getParameter("answer_num")));
			boardDTO.setAnswer_lev(Integer.parseInt(multipartRequest.getParameter("answer_lev")));
			boardDTO.setAnswer_seq(Integer.parseInt(multipartRequest.getParameter("answer_seq")));
			boardDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			result = boardDAO.boardReply(boardDTO);
			
			if(result == 0)
			{
				System.out.println("답변 실패");
				return null;
			}
			System.out.println("답변 성공");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardDetail.do?num="+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionCommand;
		
	}

}
