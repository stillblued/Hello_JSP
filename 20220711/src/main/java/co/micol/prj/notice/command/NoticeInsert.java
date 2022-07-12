package co.micol.prj.notice.command;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import co.micol.prj.comm.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;
import co.micol.prj.notice.vo.NoticeVO;


	public class NoticeInsert implements Command {

		@Override
		public String exec(HttpServletRequest request, HttpServletResponse response) {
			// 게시글 등록(파일 업로드 포함)
			NoticeService noticeDao = new NoticeServiceImpl();
			NoticeVO vo = new NoticeVO();
			
			String savePath = "C:\\Temp\\"; //파일 저장위치 (=> 실제 서버로 옮길 때는 "fileSave")	
			int uploadSize = 1024*1024*1024; //최대 파일 사이즈 : 100MB
			int n = 0;
			
			try {
				MultipartRequest multi = new MultipartRequest(request, savePath, uploadSize, "utf-8", new DefaultFileRenamePolicy());
				String originalFileName = multi.getOriginalFileName("file");
				String saveFileName = multi.getFilesystemName("file");

				vo.setNoticeWriter(multi.getParameter("noticeWriter"));
				vo.setNoticeTitle(multi.getParameter("noticeTitle"));
				vo.setNoticeDate(Date.valueOf(multi.getParameter("noticeDate")));
				vo.setNoticeSubject(multi.getParameter("noticeSubject"));
				if(originalFileName != null) {
					vo.setNoticeAttach(originalFileName);
					saveFileName = savePath + saveFileName; //파일경로를 추가한다
					vo.setNoticeAttachDir(saveFileName);
				}
				n = noticeDao.noticeInsert(vo);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String returnPage = null;
			if(n != 0) { // n==0 이면 실패, 0이 아니면 성공
				returnPage = "noticeList.do";
			} else {
				request.setAttribute("message", "게시글 등록 실패");
				returnPage = "notice/noticeError";
			}
			
			return returnPage;
		}

	}

