CREATE TABLE IF NOT EXISTS public.client
(
    clie_id SERIAL NOT NULL,
    clie_name VARCHAR(60) NOT NULL,
    clie_email VARCHAR(255) NOT NULL,
    clie_phone VARCHAR(20) NOT NULL,

    CONSTRAINT pk_client PRIMARY KEY (clie_id)
);