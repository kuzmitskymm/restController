package my.rest.restController.services;


import my.rest.restController.entity.Tiket;

import java.util.List;


public interface TiketService {

    /**
     * Создаем новый залоговый билет
     * @param tiket - залоговый билет который будем создавать
     */
    boolean create(Tiket tiket);


    /**
     * Возвращаем список всех залоговых билетов
     * @return
     */
    List<Tiket> readAll();


    /**
     * Получение информации о конкретном залоговом билете
     *
     * @param id
     * @return Tiket
     */
    Object read(long id);


    /**
     * Удаляем залоговый билет по ID
     * @param id - ID залогового билета, которого удаляем
     * @return
     */
    boolean delete(long id);


    /**
     * Обновляет залоговый билет с заданным ID
     * @param id
     * @param days
     * @return
     */
    boolean update(long id, long days);
}
