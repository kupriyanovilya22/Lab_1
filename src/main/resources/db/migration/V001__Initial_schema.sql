CREATE TABLE "document"
(
    "id"         BIGSERIAL   NOT NULL,
    "name"       VARCHAR(50) NOT NULL,
    "type"       VARCHAR(50) NOT NULL,
    "login"      VARCHAR(50) NOT NULL,
    "content"    TEXT,
    "signed_at"  TIMESTAMP(6),
    "created_at" TIMESTAMP(6)
);