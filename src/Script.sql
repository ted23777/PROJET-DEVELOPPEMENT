CREATE TABLE Personne (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    situationfamiliale VARCHAR(255),
    image VARCHAR(255)
);

INSERT INTO Personne (nom, prenom, situationfamiliale, image) VALUES
('Doe', 'John', 'Célibataire', 'image1.png'),
('Smith', 'Jane', 'Marié', 'image2.png'),
('Doe', 'Alice', 'Divorcé', 'image3.png');