
---- INSTALL SCRIPT USE DB 
-- SEQUENCE 5

--DROP USER alpha;
CREATE USER alpha WITH
  LOGIN
  SUPERUSER
  INHERIT
  NOCREATEDB
  CREATEROLE
  NOREPLICATION
  CONNECTION LIMIT -1
  PASSWORD 'alpha';


-- Table: public.sequence

--DROP TABLE public.sequence;

CREATE TABLE public.sequence
(
    seq_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    seq_count numeric(38,0),
    CONSTRAINT sequence_pkey PRIMARY KEY (seq_name)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.sequence
    OWNER to postgres;
    

CREATE TABLE public.configs
(
    config_id SERIAL not null,
    area character varying(45) COLLATE pg_catalog."default",
    config character varying(100) COLLATE pg_catalog."default",
    value character varying(100) COLLATE pg_catalog."default",
    sort integer,
    CONSTRAINT configs_pkey PRIMARY KEY (config_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.configs
    OWNER to postgres;

-- Table: public.rooms

-- DROP TABLE public.rooms;

CREATE TABLE public.rooms
(
    row_flag character varying(8) COLLATE pg_catalog."default",
    room_id SERIAL not null,
    room_name character varying(45) COLLATE pg_catalog."default",
    room_location character varying(45) COLLATE pg_catalog."default",
    room_equipment character varying(45) COLLATE pg_catalog."default",
    room_min_util integer,
    room_max_util integer,
    CONSTRAINT rooms_pkey PRIMARY KEY (room_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.rooms
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    user_id SERIAL not null,
    row_flag character varying(8) COLLATE pg_catalog."default",
    username character varying(16) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(32) COLLATE pg_catalog."default" NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    role character varying(60) COLLATE pg_catalog."default",
    c_ts timestamp without time zone,
    c_user character varying(16) COLLATE pg_catalog."default",
    m_ts timestamp without time zone,
    m_user character varying(16) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT users_username_key UNIQUE (username)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;
	

CREATE TABLE public.bookings
(
    booking_id SERIAL not null,
    row_flag character varying(20) COLLATE pg_catalog."default",
    booking_for character varying(16) COLLATE pg_catalog."default",
    booking_util integer,
    id_room integer,
    c_user character varying(16) COLLATE pg_catalog."default",
    c_ts timestamp without time zone,
    m_user character varying(16) COLLATE pg_catalog."default",
    m_ts timestamp without time zone,
    bk_from timestamp without time zone,
    bk_to timestamp without time zone,
    CONSTRAINT bookings_pkey PRIMARY KEY (booking_id),
    CONSTRAINT fk_booking_for FOREIGN KEY (booking_for)
        REFERENCES public.users (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_create_user FOREIGN KEY (c_user)
        REFERENCES public.users (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_modify_user FOREIGN KEY (m_user)
        REFERENCES public.users (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_room FOREIGN KEY (id_room)
        REFERENCES public.rooms (room_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bookings
    OWNER to postgres;

-- Table: public.configs

-- DROP TABLE public.configs;
--

--
-- TOC entry 2852 (class 0 OID 41214)
-- Dependencies: 198
-- Data for Name: configs; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.configs VALUES (101, 'TEST', 'LOGINTYPE', 'PLAIN', 10);


--
-- TOC entry 2856 (class 0 OID 41236)
-- Dependencies: 202
-- Data for Name: rooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.rooms VALUES ('ACTIVE', 1, 'Alan Turing', 'Suurstoffi 12, Erdgeschoss', 'HSLU Informatik', 37, 147);
INSERT INTO public.rooms VALUES ('ACTIVE', 2, 'Grace Hopper', 'Suurstoffi 12, Erdgeschoss', 'HSLU Informatik', 20, 78);
INSERT INTO public.rooms VALUES ('ACTIVE', 3, 'Tetris', 'Suurstoffi 41, 3. Obergeschoss', 'HSLU Informatik', 15, 59);
INSERT INTO public.rooms VALUES ('ACTIVE', 4, 'Eniac', 'Suurstoffi 41, 2. Obergeschoss', 'HSLU Informatik', 12, 48);
INSERT INTO public.rooms VALUES ('ACTIVE', 5, 'Windows', 'Suurstoffi 41, 2. Obergeschoss', 'HSLU Informatik', 8, 31);
INSERT INTO public.rooms VALUES ('ACTIVE', 6, 'Pac-Man', 'Suurstoffi 41, 2. Obergeschoss', 'HSLU Informatik', 8, 30);
INSERT INTO public.rooms VALUES ('ACTIVE', 7, 'Projektarbeitsplätze', 'Suurstoffi 41, 3. Obergeschoss, Nr. 302', 'HSLU Informatik', 7, 28);
INSERT INTO public.rooms VALUES ('ACTIVE', 8, 'Gruppenraum', 'Suurstoffi 41, 3. Obergeschoss', 'HSLU Informatik', 3, 12);
INSERT INTO public.rooms VALUES ('ACTIVE', 9, ' Auditorium', 'Horw ', 'HSLU Technik & Architektur', 49, 196);
INSERT INTO public.rooms VALUES ('ACTIVE', 10, 'Hörsaal', 'Horw ', 'HSLU Technik & Architektur', 22, 90);
INSERT INTO public.rooms VALUES ('ACTIVE', 11, 'Grosser Unterrichtsraum', 'Horw ', 'HSLU Technik & Architektur', 24, 96);
INSERT INTO public.rooms VALUES ('ACTIVE', 12, 'Kleiner Unterrichtsraum', 'Horw ', 'HSLU Technik & Architektur', 10, 40);
INSERT INTO public.rooms VALUES ('ACTIVE', 13, 'Konferenzraum', 'Horw ', 'HSLU Technik & Architektur', 11, 45);
INSERT INTO public.rooms VALUES ('ACTIVE', 15, 'Aktionshalle', 'Emmenbrücke ', 'HSLU Design & Kunst  ', 75, 300);
INSERT INTO public.rooms VALUES ('ACTIVE', 16, 'Theorieraum', 'Emmenbrücke ', 'HSLU Design & Kunst  ', 10, 40);
INSERT INTO public.rooms VALUES ('ACTIVE', 17, 'Sitzungsraum', 'Emmenbrücke ', 'HSLU Design & Kunst  ', 4, 14);
INSERT INTO public.rooms VALUES ('ACTIVE', 19, 'Niklaus Wirth', 'Suurstoffi 12, Erdgeschoss', 'HSLU Informatik', 1, 40);
INSERT INTO public.rooms VALUES ('ACTIVE', 14, 'Gruppenraum', 'Horw', 'HSLU Technik & Architektur', 3, 12);


--
-- TOC entry 2859 (class 0 OID 41270)
-- Dependencies: 205
-- Data for Name: sequence; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sequence VALUES ('SEQ_GEN_TABLE', 4200);


--
-- TOC entry 2854 (class 0 OID 41222)
-- Dependencies: 200
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (1002, NULL, 'sandro', '', 'test', NULL, 'Sandro', NULL, 'Admin', NULL, NULL, NULL, NULL);
INSERT INTO public.users VALUES (1003, NULL, 'marc', NULL, 'test', NULL, 'Marc', '2018-12-10 00:25:35.495', 'Admin', '2018-12-10 00:25:35.495', NULL, '2018-12-10 00:25:35.495', NULL);
INSERT INTO public.users VALUES (201, 'test', 'test', 'idarnold', 'test', '3204@BOTNETX', 'test', '2018-12-11 19:04:09.442', 'User', '2018-12-11 19:04:09.442', 'idarnold', '2018-12-11 19:04:09.442', 'idarnold');
INSERT INTO public.users VALUES (1, 'test', 'idarnold', 'idarnold', 'test', '8568@BOTNETX', 'Tim', '2018-12-11 19:04:14.243', 'Admin', '2018-12-11 19:04:14.243', 'idarnold', '2018-12-11 19:04:14.243', 'idarnold');


-- TOC entry 2858 (class 0 OID 41244)
-- Dependencies: 204
-- Data for Name: bookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bookings VALUES (1851, 'STORNIERT', 'test', 1, 4, 'test', '2018-12-12 17:43:39.619', 'test', '2018-12-13 15:39:12.508', '2018-12-25 15:00:00', '2018-12-25 16:00:00');
INSERT INTO public.bookings VALUES (1901, 'Hinzugefügt', 'test', 13, 4, 'test', '2018-12-13 15:40:29.549', 'test', '2018-12-13 15:40:29.549', '2018-12-25 15:40:00', '2018-12-25 20:00:00');
INSERT INTO public.bookings VALUES (1951, 'Hinzugefügt', 'test', 15, 3, 'test', '2018-12-13 15:43:43.706', 'test', '2018-12-13 15:43:43.706', '2018-12-25 15:45:00', '2018-12-25 16:30:00');
INSERT INTO public.bookings VALUES (2001, 'STORNIERT', 'test', 7, 17, 'test', '2018-12-15 00:12:42.635', 'test', '2018-12-15 00:12:52.051', '2018-12-10 08:00:00', '2018-12-11 17:00:00');
INSERT INTO public.bookings VALUES (2101, 'Hinzugefügt', 'test', 7, 19, 'test', '2018-12-15 00:24:16.354', 'test', '2018-12-15 00:24:16.354', '2018-12-11 08:00:00', '2018-12-16 12:00:00');


--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 203
-- Name: bookings_booking_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bookings_booking_id_seq', 1, false);


--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 197
-- Name: configs_config_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.configs_config_id_seq', 1, false);


--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 201
-- Name: rooms_room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rooms_room_id_seq', 19, true);


--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 199
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 1, false);


-- Completed on 2018-12-15 16:20:55


GRANT ALL ON SEQUENCE public.bookings_booking_id_seq TO alpha WITH GRANT OPTION;

GRANT ALL ON SEQUENCE public.configs_config_id_seq TO alpha WITH GRANT OPTION;

GRANT ALL ON SEQUENCE public.rooms_room_id_seq TO alpha WITH GRANT OPTION;

GRANT ALL ON SEQUENCE public.users_user_id_seq TO alpha WITH GRANT OPTION;

GRANT ALL ON TABLE public.bookings TO alpha WITH GRANT OPTION;

GRANT ALL ON TABLE public.configs TO alpha WITH GRANT OPTION;

GRANT ALL ON TABLE public.rooms TO alpha WITH GRANT OPTION;

GRANT ALL ON TABLE public.sequence TO alpha WITH GRANT OPTION;

GRANT ALL ON TABLE public.users TO alpha WITH GRANT OPTION;