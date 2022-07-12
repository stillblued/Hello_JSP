package co.micol.prj.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.prj.comm.DataSource;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<MemberVO> memberSelectList() {

		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo;
		String sql = "SELECT * FROM MEMBER";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVO();
				vo.setMemberId(rs.getString("MEMBER_ID"));
				vo.setMemberName(rs.getString("MEMBER_NAME"));
				// vo.setMemberPwd(rs.getString("MEMBER_PWD"));
				vo.setMemberAuthor(rs.getString("MEMBER_AUTHOR"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	@Override
	public MemberVO memberSelectOne(MemberVO vo) {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID =?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();

			if (rs.next()) {
				vo.setMemberId(rs.getString("MEMBER_ID"));
				vo.setMemberName(rs.getString("MEMBER_NAME"));
				// vo.setMemberPwd(rs.getString("MEMBER_PWD"));
				vo.setMemberAuthor(rs.getString("MEMBER_AUTHOR"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		int cnt = 0;
		String sql = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_NAME, MEMBER_PWD, MEMBER_AUTHOR) VALUES (?, ?, ?, ?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberName());
			psmt.setString(3, vo.getMemberPwd());
			psmt.setString(4, vo.getMemberAuthor());
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		int cnt = 0;
		String sql = "UPDATE MEMBER SET MEMBER_PWD=? MEMBER_AUTHOR=? WHERE MEMBER_ID=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberPwd());
			psmt.setString(2, vo.getMemberAuthor());
			psmt.setString(3, vo.getMemberId());
		
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		int cnt = 0;
		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public boolean isMemberIdCheck(String id) {
		boolean b = false;
		String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (!rs.next()) {
				b = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return b;
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {

		String sql = "select * from member where member_id=? and member_pwd=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPwd());

			rs = psmt.executeQuery();

			if (rs.next()) {
				vo.setMemberId(rs.getString("MEMBER_ID"));
				vo.setMemberAuthor(rs.getString("MEMBER_AUTHOR"));
				vo.setMemberName(rs.getString("MEMBER_NAME"));
				vo.setMemberPwd(rs.getString("MEMBER_PWD"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;

	}

	private void disconnect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		;

	}

}
