CREATE TABLE IF NOT EXISTS public.comment (
    comm_id SERIAL NOT NULL,
    orse_id INTEGER NOT NULL,
    comm_description TEXT NOT NULL,
    comm_send_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT pk_comment PRIMARY KEY (comm_id),
    CONSTRAINT fk_comment_order_service FOREIGN KEY (orse_id)
        REFERENCES public.order_service (orse_id)
);