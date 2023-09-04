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
    ('Cloud platform', 'Seoul', 'Development', '주요업무 - Mulesoft 운영 및 개발 (삼성전자 프로젝트)', 'Java', 2000000, '2023-09-06 12:00:00'),

    ('Finance Manager', 'Kyungki', 'Business', '주요업무 - 회사의 재정을 기획하고 관리하며, 회사재정의 성장 및 안정을 위해 고민합니다. 재무/회계/세무업무를 성공적으로 수행하고 리스크를 파악하여 관리합니다.', 'Excel', 1500000, '2023-09-07 12:00:00'),

    ('Marketer', 'Seoul', 'Marketing', '주요업무 - 보드게임 및 교육보드게임 마케팅을 위한 컨텐츠마케팅 / 디지털마케팅 담당. 관련업종 콜라보 기획 및 행사기획. 주요 프러덕트 마케팅전략 수립지원.', '', 1000000, '2023-09-08 12:00:00'),

    ('Content Teamleader', 'Busan', 'Education', '주요업무 - 학습 콘텐츠 전략 수립 및 실행. TOEFL & IELTS 시험 관련 모의고사, 연습문제, 영상강의, 추가 학습 자료 등 콘텐츠 기획', 'TOEFL', 2000000, '2023-09-09 12:00:00'),

    ('Tech Leader', 'Seoul', 'Development', '주요업무 - 인프라 관리 및 운영', 'Docker', 3000000, '2023-09-10 12:00:00');
