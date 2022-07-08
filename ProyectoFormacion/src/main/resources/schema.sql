CREATE SCHEMA IF NOT EXISTS superheroes;
USE superheroes;

CREATE TABLE IF NOT EXISTS poder
(
    id integer NOT NULL DEFAULT ,
    nombre character VARCHAR(255) NOT NULL,
    CONSTRAINT poder_pkey PRIMARY KEY (id),
    CONSTRAINT uk_keums5xsh241ruoqplxfgiqp1 UNIQUE (nombre)
)



CREATE TABLE IF NOT EXISTS superheroe
(
    id integer NOT NULL DEFAULT ),
    estado boolean NOT NULL,
    id_universo integer NOT NULL,
    nombre character VARCHAR(255) ,
    CONSTRAINT superheroe_pkey PRIMARY KEY (id),
    CONSTRAINT uk_inpexstiw10ad85tkm9o3klul UNIQUE (nombre),
    CONSTRAINT fk70lrpm4v0nefgkwsqo6b3hdq8 FOREIGN KEY (id_universo)
        REFERENCES public.universo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS universo
(
    id integer NOT NULL ,
    nombre character VARCHAR(255) NOT NULL,
    CONSTRAINT universo_pkey PRIMARY KEY (id),
    CONSTRAINT uk_kxnpg8lh42v9aq30hax5h1knl UNIQUE (nombre)
)


CREATE TABLE IF NOT EXISTS superheroe_poder
(
    superheroe_id integer NOT NULL,
    poder_id integer NOT NULL,
    CONSTRAINT fka9i5rqmi85l9w2q6n612q968r FOREIGN KEY (superheroe_id)
        REFERENCES public.superheroe (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkf4a1v7h84l6tonkcuk362s9ob FOREIGN KEY (poder_id)
        REFERENCES public.poder (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
