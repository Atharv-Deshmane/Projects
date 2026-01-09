package com.marvellous.MarvellousFullStack.Controller;

import com.marvellous.MarvellousFullStack.Entity.BatchEntry;
import com.marvellous.MarvellousFullStack.Service.BatchEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batches")
public class BatchEntryController
{
    @Autowired
    private BatchEntryService batchEntryService;

    @GetMapping
    public ResponseEntity<?> getAll()
    {
        List<BatchEntry> alldata = batchEntryService.getAll();

        if(alldata != null && !alldata.isEmpty())
        {
            return new ResponseEntity<>(alldata, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BatchEntry> createEntry(@RequestBody BatchEntry myEntry)
    {
        try
        {
            batchEntryService.saveEntry(myEntry);
            return new ResponseEntity<BatchEntry>(myEntry,HttpStatus.CREATED);
        } catch (Exception e)
        {
            return new ResponseEntity<BatchEntry>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid)
    {
        batchEntryService.deleteById(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myid}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId , @RequestBody BatchEntry myentry)
    {
        BatchEntry old = batchEntryService.findById(myId).orElse(null);

        if (old != null)
        {
            old.setName(myentry.getName());
            old.setFees(myentry.getFees());
            batchEntryService.saveEntry(null);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
