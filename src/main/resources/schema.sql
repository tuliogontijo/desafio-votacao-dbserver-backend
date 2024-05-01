CREATE TABLE IF NOT EXISTS "pautas" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"title" text NOT NULL,
	"description" text,
	"session_opennig_date_time" timestamp with time zone,
	"expiration_date_time" timestamp with time zone,
	"created_at" timestamp with time zone NOT NULL DEFAULT 'now()',
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "votos" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"cpf" text NOT NULL,
	"vote" text NOT NULL,
	"created_at" timestamp with time zone NOT NULL DEFAULT 'now()',
	"pauta_id" bigint NOT NULL,
	PRIMARY KEY ("id"),
	FOREIGN KEY ("pauta_id") REFERENCES "pautas"("id")
);