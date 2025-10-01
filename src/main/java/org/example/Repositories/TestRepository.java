package org.example.Repositories;

import org.example.Model.OldModels.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TestRepository extends CrudRepository<Test, UUID> {


}
