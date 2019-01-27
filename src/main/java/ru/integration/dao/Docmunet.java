package ru.integration.dao;

import ru.integration.entities.PersonEntity;
import ru.integration.entities.DocumentEntity;

public interface Docmunet {
    DocumentEntity getDocumentByPerson(PersonEntity aPerson);
}
