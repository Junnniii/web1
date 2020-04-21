package jun.board.service;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jun.board.action.Action;
import jun.board.command.ActionCommand;
import jun.board.dao.BoardDAO;
import jun.board.model.BoardDTO;

public class BoardAddService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		ActionCommand actionCommand = new ActionCommand();
		String realFolder = "";
		String saveFolder = "./boardUpload";
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		
		int fileSize = 10*1024*1024;
		boolean result = false;
		try {
			
			MultipartRequest multipartRequest = new MultipartRequest(request, realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			boardDTO.setName(multipartRequest.getParameter("name"));
			boardDTO.setPass(multipartRequest.getParameter("pass"));
			boardDTO.setSubject(multipartRequest.getParameter("subject"));
			boardDTO.setContent(multipartRequest.getParameter("content"));
			boardDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			
			result = boardDAO.boardInsert(boardDTO);
			
			if(result == false)
			{
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			
			actionCommand.setRedirect(true);
			
			actionCommand.setPath("./BoardList.do");
			return actionCommand;
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
