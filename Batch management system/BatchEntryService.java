package com.marvellous.MarvellousFullStack.Service;

import com.marvellous.MarvellousFullStack.Entity.BatchEntry;
import com.marvellous.MarvellousFullStack.Repository.BatchEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BatchEntryService
{
    @Autowired
    private BatchEntryRepository batchEntryRepository;

    public void saveEntry(BatchEntry batchEntry)
    {
        batchEntryRepository.save(batchEntry);
    }

    public List<BatchEntry> getAll()
    {
        return batchEntryRepository.findAll();
    }

    // R:Read get
    public Optional<BatchEntry> findById(Object id)
    {
       return batchEntryRepository.findById((ObjectId) id);
    }

    // D : Delete
    public void deleteById(ObjectId id)
    {
        batchEntryRepository.deleteById(id);
    }
}
