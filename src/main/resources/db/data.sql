/*create table EMPLOY_POSTING
  (
      POSTING_ID BIGINT NOT NULL PRIMARY KEY,
      POSTING_NAME VARCHAR(255) ,
      REGION      VARCHAR(255) ,
      JOB_GROUP    VARCHAR(255) ,
      CONTENT     VARCHAR(255) ,
      TECH_STACK   VARCHAR(255) ,
      WAGE        BIGINT,
      DEAD_LINE    DATE
  );*/


INSERT INTO EMPLOY_POSTING (POSTING_NAME, REGION, JOB_GROUP, CONTENT, TECH_STACK, WAGE, DEAD_LINE)
VALUES
    ('클라우드 플랫폼', '서울', '개발', '주요업무 - Mulesoft 운영 및 개발 (삼성전자 프로젝트)', 'Java', 2000000, '2023-09-16 12:00:00'),

    ('Back-End 개발자(블록체인 솔루션)', '부산', '개발', '주요업무 - NFT, STO, DID, PDS 기술들을 활용한 블록체인 솔루션 개발', 'Python', 2000000, '2023-09-11 12:00:00'),

    ('Finance Manager', '경기', '경영·비즈니스', '주요업무 - 회사의 재정을 기획하고 관리하며, 회사재정의 성장 및 안정을 위해 고민합니다. 재무/회계/세무업무를 성공적으로 수행하고 리스크를 파악하여 관리합니다.', 'Excel', 1500000, '2023-09-17 12:00:00'),

    ('퍼포먼스 마케터', '인천', '마케팅·광고', '주요업무 - 보드게임 및 교육보드게임 마케팅을 위한 컨텐츠마케팅 / 디지털마케팅 담당. 관련업종 콜라보 기획 및 행사기획. 주요 프러덕트 마케팅전략 수립지원.', 'Google Analytics', 1000000, '2023-09-18 12:00:00'),

    ('플랫폼 웹 개발자(아웃도어&스포츠)', '대전', '개발', '주요업무 - 아웃도어 마켓플레이스 개발 및 운영(E-Commerce 플랫폼)', 'Spring Boot', 1000000 , '2023-09-19 12:00:00'),

    ('Linux 개발 교육 운영 매니저 (교육개발팀)', '광주', '교육', '주요업무 - 교육 환경 관리 및 교육 과정 운영', 'Slack', 2000000, '2023-09-19 12:00:00'),

    ('Tech Leader', '대구', '개발', '주요업무 - 인프라 관리 및 운영', 'Docker', 3000000, '2023-09-20 12:00:00');


INSERT INTO USERS (
DTYPE, USER_ID, EMAIL, PASSWORD, USER_ROLES) values ('NormalUser', 9999999, 'admin', '$2a$10$FF.wng7H3/bSzyZtJCYnPeSQwMyvT22NGnV2FMnPnl8DFTObVg1xm', 'ROLE_ADMIN');
