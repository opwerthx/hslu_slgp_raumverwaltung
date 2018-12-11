--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

-- Started on 2018-12-11 20:37:27

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2865 (class 1262 OID 13012)
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'German_Switzerland.1252' LC_CTYPE = 'German_Switzerland.1252';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2858 (class 0 OID 41244)
-- Dependencies: 204
-- Data for Name: bookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bookings (booking_id, row_flag, booking_for, booking_util, id_room, c_user, c_ts, m_user, m_ts, bk_from, bk_to) FROM stdin;
1451	STORNIERT	sandro	5	17	sandro	2018-12-11 19:03:53.709	idarnold	2018-12-11 19:13:44.139	2018-12-17 08:00:00	2018-12-10 12:00:00
1501	Hinzugefügt	idarnold	6	19	idarnold	2018-12-11 19:34:16.648	idarnold	2018-12-11 19:34:16.648	2018-12-17 09:00:00	2018-12-14 18:00:00
1551	Hinzugefügt	test	5	19	test	2018-12-11 19:57:30.805	test	2018-12-11 19:57:30.805	2018-12-03 10:00:00	2018-12-14 22:00:00
1552	Hinzugefügt	marc	39	11	idarnold	2018-12-11 19:58:20.521	idarnold	2018-12-11 19:58:20.521	2018-12-03 10:00:00	2018-12-17 22:00:00
\.


--
-- TOC entry 2852 (class 0 OID 41214)
-- Dependencies: 198
-- Data for Name: configs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.configs (config_id, area, config, value, sort) FROM stdin;
101	TEST	LOGINTYPE	PLAIN	10
\.


--
-- TOC entry 2856 (class 0 OID 41236)
-- Dependencies: 202
-- Data for Name: rooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rooms (row_flag, room_id, room_name, room_location, room_equipment, room_min_util, room_max_util) FROM stdin;
ACTIVE	1	Alan Turing	Suurstoffi 12, Erdgeschoss	HSLU Informatik	37	147
ACTIVE	2	Grace Hopper	Suurstoffi 12, Erdgeschoss	HSLU Informatik	20	78
ACTIVE	3	Tetris	Suurstoffi 41, 3. Obergeschoss	HSLU Informatik	15	59
ACTIVE	4	Eniac	Suurstoffi 41, 2. Obergeschoss	HSLU Informatik	12	48
ACTIVE	5	Windows	Suurstoffi 41, 2. Obergeschoss	HSLU Informatik	8	31
ACTIVE	6	Pac-Man	Suurstoffi 41, 2. Obergeschoss	HSLU Informatik	8	30
ACTIVE	7	Projektarbeitsplätze	Suurstoffi 41, 3. Obergeschoss, Nr. 302	HSLU Informatik	7	28
ACTIVE	8	Gruppenraum	Suurstoffi 41, 3. Obergeschoss	HSLU Informatik	3	12
ACTIVE	9	 Auditorium	Horw 	HSLU Technik & Architektur	49	196
ACTIVE	10	Hörsaal	Horw 	HSLU Technik & Architektur	22	90
ACTIVE	11	Grosser Unterrichtsraum	Horw 	HSLU Technik & Architektur	24	96
ACTIVE	12	Kleiner Unterrichtsraum	Horw 	HSLU Technik & Architektur	10	40
ACTIVE	13	Konferenzraum	Horw 	HSLU Technik & Architektur	11	45
ACTIVE	15	Aktionshalle	Emmenbrücke 	HSLU Design & Kunst  	75	300
ACTIVE	16	Theorieraum	Emmenbrücke 	HSLU Design & Kunst  	10	40
ACTIVE	17	Sitzungsraum	Emmenbrücke 	HSLU Design & Kunst  	4	14
ACTIVE	19	Niklaus Wirth	Suurstoffi 12, Erdgeschoss	HSLU Informatik	1	40
ACTIVE	14	Gruppenraum	Horw	HSLU Technik & Architektur	3	12
\.


--
-- TOC entry 2859 (class 0 OID 41270)
-- Dependencies: 205
-- Data for Name: sequence; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sequence (seq_name, seq_count) FROM stdin;
SEQ_GEN_TABLE	1600
\.


--
-- TOC entry 2854 (class 0 OID 41222)
-- Dependencies: 200
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, row_flag, username, email, password, firstname, name, create_time, role, c_ts, c_user, m_ts, m_user) FROM stdin;
1002	\N	sandro		test	\N	Sandro	\N	Admin	\N	\N	\N	\N
1003	\N	marc	\N	test	\N	Marc	2018-12-10 00:25:35.495	Admin	2018-12-10 00:25:35.495	\N	2018-12-10 00:25:35.495	\N
201	test	test	idarnold	test	7208@BOTNETX	test	2018-12-11 19:04:09.442	User	2018-12-11 19:04:09.442	idarnold	2018-12-11 19:04:09.442	idarnold
1	test	idarnold	idarnold	test	7208@BOTNETX	Tim	2018-12-11 19:04:14.243	Admin	2018-12-11 19:04:14.243	idarnold	2018-12-11 19:04:14.243	idarnold
\.


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


-- Completed on 2018-12-11 20:37:27

--
-- PostgreSQL database dump complete
--

