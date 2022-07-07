package co.micol.prj.board;

public class BoardVO {
	
	
private String id; //글번호?
private String title; //제목
private String content; //내용
private String writer; //작성자
private String rdt; //작성일
private String hit; //조회수


public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getWriter() {
	return writer;
}
public void setWriter(String writer) {
	this.writer = writer;
}
public String getRdt() {
	return rdt;
}
public void setRdt(String rdt) {
	this.rdt = rdt;
}
public String getHit() {
	return hit;
}
public void setHit(String hit) {
	this.hit = hit;
}




}
