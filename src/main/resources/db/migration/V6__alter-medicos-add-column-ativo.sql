ALTER TABLE medicos ADD COLUMN ativo BOOLEAN;
UPDATE TABLE medicos SET ativo = true;