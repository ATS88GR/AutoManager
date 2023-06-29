package com.education.projects.cars.manager.carsmanager.service;

public class StartService {

/**
 * Starts the database connection pool class
 * @see DBPoolService
 * and start menu
 */

    public void run() {
        DBPoolService.getInstance().setConnection();
        new DialogMenuService().chooseAction();         //actions in start menu
        DBPoolService.getInstance().closeConnection();
    }
}
