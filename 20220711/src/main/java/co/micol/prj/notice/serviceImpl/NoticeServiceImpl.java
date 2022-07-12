package co.micol.prj.notice.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.micol.prj.comm.DataSource;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.vo.NoticeVO;

public class NoticeServiceImpl implements NoticeService {

	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<NoticeVO> noticeSelectList() {
		List<NoticeVO> list = new ArrayList<>();
		NoticeVO vo;
		String sql = "SELECT * FROM NOTICE ORDER BY NOTICE_ID DESC";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new NoticeVO();
				vo.setNoticeId(rs.getInt("NOTICE_ID"));
				vo.setNoticeWriter(rs.getString("NOTICE_WRITER"));
				vo.setNoticeTitle(rs.getString("NOTICE_TITLE"));
				vo.setNoticeDate(rs.getDate("NOTICE_DATE"));
				vo.setNoticeHit(rs.getInt("NOTICE_HIT"));
				vo.setNoticeAttach(rs.getString("NOTICE_ATTACH"));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {
		String sql = "SELECT * FROM NOTICE WHERE NOTICE_ID =?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getNoticeId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new NoticeVO();
				vo.setNoticeId(rs.getInt("NOTICE_ID"));
				vo.setNoticeWriter(rs.getString("NOTICE_WRITER"));
				vo.setNoticeTitle(rs.getString("NOTICE_TITLE"));
				vo.setNoticeSubject(rs.getString("NOTICE_SUBJECT"));
				vo.setNoticeDate(rs.getDate("NOTICE_DATE"));
				vo.setNoticeHit(rs.getInt("NOTICE_HIT"));
				vo.setNoticeAttach(rs.getString("NOTICE_ATTACH"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		// 글 등록
		int n = 0;
		String sql = "INSERT INTO NOTICE VALUES(NOTICE_SEQ.NEXTVAL, ?, ?, ?, ?, 0, ?, ?)";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getNoticeWriter());
			psmt.setString(2, vo.getNoticeTitle());
			psmt.setString(3, vo.getNoticeSubject());
			psmt.setDate(4, vo.getNoticeDate());
			psmt.setString(5, vo.getNoticeAttach());
			psmt.setString(6, vo.getNoticeAttachDir());
			
			n=psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int noticeUpdate(NoticeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int noticeDelete(NoticeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NoticeVO> noticeSearchList(String key, String val) {
		// 목록검색
		List<NoticeVO> list = new ArrayList<>();
		NoticeVO vo;
		String sql = "SELECT * FROM NOTICE WHERE ? LIKE %?%";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, key);
			psmt.setString(2, val);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new NoticeVO();
				vo.setNoticeId(rs.getInt("NOTICE_ID"));
				vo.setNoticeWriter(rs.getString("NOTICE_WRITER"));
				vo.setNoticeTitle(rs.getString("NOTICE_TITLE"));
				vo.setNoticeDate(rs.getDate("NOTICE_DATE"));
				vo.setNoticeSubject(rs.getString("NOTICE_SUBJECT"));
				vo.setNoticeAttach(rs.getString("NOTICE_ATTACH"));
				vo.setNoticeHit(rs.getInt("NOTICE_HIT"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
