
    CREATE OR REPLACE FUNCTION update_time()
    RETURNS TRIGGER AS $$
    BEGIN
            NEW.updated_at = now();
    RETURN NEW;
    END;
    $$ language 'plpgsql';

