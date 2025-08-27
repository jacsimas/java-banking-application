CREATE TABLE customer (
  customer_id  UUID PRIMARY KEY,
  full_name    TEXT NOT NULL,
  email        TEXT UNIQUE,
  phone        TEXT,
  kyc_status   TEXT NOT NULL CHECK (kyc_status IN ('PENDING','VERIFIED','REJECTED')),
  created_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at   TIMESTAMPTZ
);