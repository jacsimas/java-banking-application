CREATE TABLE account (
  account_id  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  customer_id BIGINT NOT NULL REFERENCES customers(id),
  currency    CHAR(3) NOT NULL,
  iban        VARCHAR(34),
  bic         VARCHAR(11),
  balance     NUMERIC(18,2) NOT NULL DEFAULT 0,
  status      TEXT NOT NULL CHECK (status IN ('ACTIVE','FROZEN','CLOSED')),
  created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),

  CONSTRAINT account_iban_chars CHECK (iban IS NULL OR iban ~ '^[A-Z0-9]+$')
);