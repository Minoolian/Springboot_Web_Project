-- H2 메모리 초기화의 번거로움 해소
insert into posts(id,title, author, content, create_date, modified_date) values(hibernate_sequence.nextval,'테스트1','minoolian1','test1',now(),now());
insert into posts(id,title, author, content, create_date, modified_date) values(hibernate_sequence.nextval,'테스트2','minoolian2','test2',now(),now());