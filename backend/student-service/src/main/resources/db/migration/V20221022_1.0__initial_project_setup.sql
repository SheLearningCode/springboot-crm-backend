--
-- Name: course; Type: TABLE; Schema: public; Owner: student_crm_service
--
create table course
(
    id   bigint not null,
    name character varying(255)
);


alter table course OWNER to student_crm_service;

--
-- Name: course_enrolments; Type: TABLE; Schema: public; Owner: student_crm_service
--

create table course_enrolments
(
    course_id     bigint not null,
    enrolments_id bigint not null
);


alter table course_enrolments OWNER to student_crm_service;

--
-- Name: courses_students; Type: TABLE; Schema: public; Owner: student_crm_service
--

create table courses_students
(
    students_id bigint not null,
    courses_id  bigint not null
);


alter table courses_students OWNER to student_crm_service;

--
-- Name: enrolment; Type: TABLE; Schema: public; Owner: student_crm_service
--

create table enrolment
(
    id         bigint not null,
    course_id  bigint,
    student_id bigint
);


alter table enrolment OWNER to student_crm_service;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: student_crm_service
--

create sequence hibernate_sequence start with 1 increment by 1 no minvalue no maxvalue CACHE 1;


alter table hibernate_sequence OWNER to student_crm_service;

--
-- Name: student; Type: TABLE; Schema: public; Owner: student_crm_service
--

create table student
(
    id         bigint not null,
    email      character varying(255),
    first_name character varying(255),
    last_name  character varying(255)
);


alter table student OWNER to student_crm_service;

--
-- Name: student_enrolments; Type: TABLE; Schema: public; Owner: student_crm_service
--

create table student_enrolments
(
    student_id    bigint not null,
    enrolments_id bigint not null
);


alter table student_enrolments OWNER to student_crm_service;

--
-- Name: course_enrolments course_enrolments_pkey; Type: CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY course_enrolments add constraint course_enrolments_pkey primary key (course_id, enrolments_id);


--
-- Name: course course_pkey; Type: CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY course add constraint course_pkey primary key (id);


--
-- Name: courses_students courses_students_pkey; Type: CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY courses_students add constraint courses_students_pkey primary key (students_id, courses_id);


--
-- Name: enrolment enrolment_pkey; Type: CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY enrolment add constraint enrolment_pkey primary key (id);



--
-- Name: student_enrolments student_enrolments_pkey; Type: CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY student_enrolments add constraint student_enrolments_pkey primary key (student_id, enrolments_id);


--
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY student add constraint student_pkey primary key (id);


--
-- Name: student_enrolments uk_4mv4duruoy704leua1hx8yxos; Type: CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY student_enrolments add constraint uk_4mv4duruoy704leua1hx8yxos unique (enrolments_id);


--
-- Name: course_enrolments uk_68r79teg49wgubrscpoifolcm; Type: CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY course_enrolments add constraint uk_68r79teg49wgubrscpoifolcm unique (enrolments_id);


--
-- Name: student ukfe0i52si7ybu0wjedj6motiim; Type: CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY student add constraint ukfe0i52si7ybu0wjedj6motiim unique (email);


--
-- Name: student_enrolments fkdl6ieex3e4rre2rtkxeh3jef8; Type: FK CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY student_enrolments add constraint fkdl6ieex3e4rre2rtkxeh3jef8 foreign key (enrolments_id) references enrolment(id);


--
-- Name: student_enrolments fkgk4tvje0bu0yk0j4d1ftf58op; Type: FK CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY student_enrolments add constraint fkgk4tvje0bu0yk0j4d1ftf58op foreign key (student_id) references student(id);


--
-- Name: course_enrolments fklf87xvd6gta08kny6k5qmfjb9; Type: FK CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY course_enrolments add constraint fklf87xvd6gta08kny6k5qmfjb9 foreign key (course_id) references course(id);


--
-- Name: course_enrolments fkni4lak7n95xe2r3u9edrn67ax; Type: FK CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY course_enrolments add constraint fkni4lak7n95xe2r3u9edrn67ax foreign key (enrolments_id) references enrolment(id);


--
-- Name: enrolment fkqnrv6xltxnx61nfjoe2sngny4; Type: FK CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY enrolment add constraint fkqnrv6xltxnx61nfjoe2sngny4 foreign key (course_id) references course(id);


--
-- Name: enrolment fkquem30hnspsnegde2q2bhvou; Type: FK CONSTRAINT; Schema: public; Owner: student_crm_service
--

alter table ONLY enrolment add constraint fkquem30hnspsnegde2q2bhvou foreign key (student_id) references student(id);


--
-- PostgreSQL database dump complete
--

