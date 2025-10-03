ALTER TABLE customer
ALTER COLUMN customer_id
SET DEFAULT gen_random_uuid();