CREATE TABLE IF NOT EXISTS public.order_service (
    orse_id SERIAL NOT NULL,
    clie_id INTEGER NOT NULL,
    orse_description TEXT NOT NULL,
    orse_price NUMERIC NOT NULL,
    orse_status VARCHAR(30) NOT NULL,
    orse_start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    orse_end_date TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT pk_order_service PRIMARY KEY (orse_id),
    CONSTRAINT fk_order_service_client FOREIGN KEY (clie_id)
        REFERENCES public.client (clie_id)
);