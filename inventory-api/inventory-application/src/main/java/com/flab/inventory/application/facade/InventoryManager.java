package com.flab.inventory.application.facade;

import com.flab.inventory.application.CloseInventoryProcessor;
import com.flab.inventory.application.IncreaseInventoryProcessor;
import com.flab.inventory.application.OpenInventoryProcessor;
import com.flab.inventory.application.OrderPayedProcessor;
import com.flab.inventory.application.ReduceInventoryProcessor;
import com.flab.inventory.application.command.CloseInventoryCommand;
import com.flab.inventory.application.command.IncreaseInventoryCommand;
import com.flab.inventory.application.command.OpenInventoryCommand;
import com.flab.inventory.application.command.OrderCompletedCommand;
import com.flab.inventory.application.command.ReduceInventoryCommand;
import org.springframework.stereotype.Service;

@Service
public class InventoryManager {

    private final OpenInventoryProcessor openInventoryProcessor;
    private final CloseInventoryProcessor closeInventoryProcessor;
    private final IncreaseInventoryProcessor increaseInventoryProcessor;
    private final ReduceInventoryProcessor reduceInventoryProcessor;
    private final OrderPayedProcessor orderCompletedProcessor;


    public InventoryManager(
        OpenInventoryProcessor openInventoryProcessor,
        CloseInventoryProcessor closeInventoryProcessor,
        IncreaseInventoryProcessor increaseInventoryProcessor,
        ReduceInventoryProcessor reduceInventoryProcessor,
        OrderPayedProcessor orderCompletedProcessor
    ) {
        this.openInventoryProcessor = openInventoryProcessor;
        this.closeInventoryProcessor = closeInventoryProcessor;
        this.increaseInventoryProcessor = increaseInventoryProcessor;
        this.reduceInventoryProcessor = reduceInventoryProcessor;
        this.orderCompletedProcessor = orderCompletedProcessor;
    }

    public void open(OpenInventoryCommand command) {
        openInventoryProcessor.execute(command);
    }

    public void close(CloseInventoryCommand command) {
        closeInventoryProcessor.execute(command);
    }

    public void increase(IncreaseInventoryCommand command) {
        increaseInventoryProcessor.execute(command);
    }

    public void reduce(ReduceInventoryCommand command) {
        reduceInventoryProcessor.execute(command);
    }

    public void orderCompleted(OrderCompletedCommand command) {
        orderCompletedProcessor.execute(command);
    }
}
