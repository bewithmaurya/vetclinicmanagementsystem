CREATE TABLE ADMIN_DETAILS
(
	FULLNAME VARCHAR2(30),
	USERID VARCHAR2(20),
	SECURITYQUESTION VARCHAR2(50),
	ANSWER VARCHAR2(20),
	PASSWORD VARCHAR2(20)
);

CREATE TABLE RECEPTIONIST_DETAILS
(
	ID VARCHAR2(20),
	NAME VARCHAR2(30),
	DOB DATE,
	GENDER VARCHAR2(20),
	MOBILE_NO VARCHAR2(13),
	EMAILID VARCHAR2(50),
	ADDRESS VARCHAR2(100),
	USERID VARCHAR2(30),
	SECURITY_QUESTION VARCHAR2(50),
	ANSWER VARCHAR2(30),
	PASSWORD VARCHAR2(30)
);

CREATE TABLE DOCTOR_DETAILS
(
	ID VARCHAR2(20),
	NAME VARCHAR2(30),
	DOB DATE,
	GENDER VARCHAR2(20),
	QUALIFICATION VARCHAR2(30),
	SPECIALIZATION VARCHAR2(30),
	MOBILE_NO VARCHAR2(13),
	EMAILID VARCHAR2(50),
	ADDRESS VARCHAR2(100),
	USERID VARCHAR2(30),
	SECURITY_QUESTION VARCHAR2(30),
	ANSWER VARCHAR2(30),
	PASSWORD VARCHAR2(30)
);

CREATE TABLE EMPLOYEE_DETAILS
(
	ID VARCHAR2(20),
	NAME VARCHAR2(30),
	DOB DATE,
	GENDER VARCHAR2(20),
	QUALIFICATION VARCHAR2(30),
	POST VARCHAR2(30),
	MOBILE_NO VARCHAR2(13),
	EMAILID VARCHAR2(50),
	ADDRESS VARCHAR2(100),
	USERID VARCHAR2(30),
	SECURITY_QUESTION VARCHAR2(30),
	ANSWER VARCHAR2(30),
	PASSWORD VARCHAR2(30)
);

CREATE TABLE ANIMAL_DETAILS
(
	REG_DATE DATE,
	REG_NO VARCHAR2(20),
	OWNER_NAME VARCHAR2(30),
	PET_NAME VARCHAR2(20),
	GENDER VARCHAR2(20),
	AGE VARCHAR2(10),
	SPECIES VARCHAR2(20),
	BREED VARCHAR2(30),
	HEIGHT VARCHAR2(20),
	WEIGHT VARCHAR2(20),
	MOBILE_NO VARCHAR2(13),
	EMAILID VARCHAR2(50),
	STATE VARCHAR2(20),
	ADDRESS VARCHAR2(150),
	CURRENT_CONDITION VARCHAR2(250),
	MEDICAL_HISTORY VARCHAR2(250)
);


CREATE TABLE APPOINTMENT
(
	REG_NO VARCHAR2(20),
	CURDATE DATE,
	CURTIME VARCHAR2(30),
	APPOINTMENT_NO VARCHAR2(40),
	OWNER_NAME VARCHAR2(20),
	PET_NAME VARCHAR2(20),
	MOBILE_NO VARCHAR2(20),
	GENDER VARCHAR2(20),
	AGE VARCHAR2(10),
	HEIGHT VARCHAR2(20),
	WEIGHT VARCHAR2(30),
	APPOINTMENT_DATE DATE,
	APPOINTMENT_TIME VARCHAR2(30),
	PROBLEM VARCHAR2(30),
	SERVICES VARCHAR2(70),
	FEE VARCHAR2(30),
	DOC_EMP_NAME VARCHAR2(20)
);


CREATE TABLE SERVICE_DETAILS
(
	ID VARCHAR2(30),
	SERVICE VARCHAR2(80),
	FEE VARCHAR2(50)
);

CREATE TABLE MEDICINE_DETAILS
(
	ID VARCHAR2(30),
	NAME VARCHAR2(50),
	DRUGS VARCHAR2(500),
	TYPE VARCHAR2(50),
	COMPANY VARCHAR2(30)
);

CREATE TABLE VACCINATION_DETAILS
(
	ID VARCHAR2(30),
	VACCINATION VARCHAR2(50)
);

CREATE TABLE DRUGS_DETAILS
(
	DRUGS_ID VARCHAR2(20),
	DRUGS_NAME VARCHAR2(50),
	DESCRIPTION VARCHAR2(100)
);

CREATE TABLE EMP_TREATMENT
(
	CURDATE DATE,
	PRESC_NO VARCHAR2(20),
	APMNT_NO VARCHAR2(30),
	REG_NO VARCHAR2(30),
	EMPNAME VARCHAR2(50),
	OWNER_NAME VARCHAR2(50),
	PET_NAME VARCHAR2(30),
	SERVICE VARCHAR2(30),
	STATUS VARCHAR2(30)
);

CREATE TABLE DOC_TREATMENT
(
	CURDATE DATE,
	PRESC_NO VARCHAR2(20),
	APMNT_NO VARCHAR2(30),
	REG_NO VARCHAR2(30),
	DOCNAME VARCHAR2(50),
	OWNER_NAME VARCHAR2(50),
	PET_NAME VARCHAR2(30),
	SERVICE VARCHAR2(30),
	PRESCRIPTION VARCHAR2(200)
);

INSERT INTO ADMIN_DETAILS VALUES('ADMINISTRATOR','admin','FAVOURITE COLOR','BLUE','admin');

COMMIT;
\
QUIT
\





