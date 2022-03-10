--
-- PostgreSQL database dump
--

-- Dumped from database version 12.10 (Ubuntu 12.10-1.pgdg20.04+1)
-- Dumped by pg_dump version 12.10 (Ubuntu 12.10-1.pgdg20.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: animals; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.animals (
    id integer NOT NULL,
    name character varying,
    agerange character varying,
    health character varying,
    rangerid integer,
    type character varying,
    location character varying
);


ALTER TABLE public.animals OWNER TO access;

--
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.animals_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.animals_id_seq OWNER TO access;

--
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.animals_id_seq OWNED BY public.animals.id;


--
-- Name: locations; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.locations (
    id integer NOT NULL,
    name character varying,
    latitude numeric(4,2),
    longitude numeric(5,2)
);


ALTER TABLE public.locations OWNER TO access;

--
-- Name: locations_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.locations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.locations_id_seq OWNER TO access;

--
-- Name: locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.locations_id_seq OWNED BY public.locations.id;


--
-- Name: rangers; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.rangers (
    id integer NOT NULL,
    name character varying,
    contact character varying,
    badgeid integer
);


ALTER TABLE public.rangers OWNER TO access;

--
-- Name: rangers_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.rangers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rangers_id_seq OWNER TO access;

--
-- Name: rangers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.rangers_id_seq OWNED BY public.rangers.id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: access
--

CREATE TABLE public.sightings (
    id integer NOT NULL,
    rangerid integer,
    animalid integer,
    locationid integer,
    timesighted timestamp without time zone
);


ALTER TABLE public.sightings OWNER TO access;

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: access
--

CREATE SEQUENCE public.sightings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sightings_id_seq OWNER TO access;

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: access
--

ALTER SEQUENCE public.sightings_id_seq OWNED BY public.sightings.id;


--
-- Name: animals id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.animals ALTER COLUMN id SET DEFAULT nextval('public.animals_id_seq'::regclass);


--
-- Name: locations id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.locations ALTER COLUMN id SET DEFAULT nextval('public.locations_id_seq'::regclass);


--
-- Name: rangers id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.rangers ALTER COLUMN id SET DEFAULT nextval('public.rangers_id_seq'::regclass);


--
-- Name: sightings id; Type: DEFAULT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.sightings ALTER COLUMN id SET DEFAULT nextval('public.sightings_id_seq'::regclass);


--
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.animals (id, name, agerange, health, rangerid, type, location) FROM stdin;
\.


--
-- Data for Name: locations; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.locations (id, name, latitude, longitude) FROM stdin;
\.


--
-- Data for Name: rangers; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.rangers (id, name, contact, badgeid) FROM stdin;
\.


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: access
--

COPY public.sightings (id, rangerid, animalid, locationid, timesighted) FROM stdin;
\.


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.animals_id_seq', 1, false);


--
-- Name: locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.locations_id_seq', 1, false);


--
-- Name: rangers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.rangers_id_seq', 1, false);


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: access
--

SELECT pg_catalog.setval('public.sightings_id_seq', 1, false);


--
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);


--
-- Name: rangers rangers_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.rangers
    ADD CONSTRAINT rangers_pkey PRIMARY KEY (id);


--
-- Name: sightings sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: access
--

ALTER TABLE ONLY public.sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

