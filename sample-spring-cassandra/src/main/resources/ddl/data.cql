USE sample;

INSERT INTO users JSON '{"user_id": 0, "user_name": "org.debugroom", "first_name": "org", "last_name": "debugroom" }' IF NOT EXISTS ;
INSERT INTO users JSON '{"user_id": 1, "user_name": "(ΦωΦ)", "first_name": "(・∀・)", "last_name": "(・ω・`)" }' IF NOT EXISTS ;