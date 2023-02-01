package my.rest.restController.services;


import my.rest.restController.entity.Tiket;
import my.rest.restController.repositories.TiketRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TiketServiceImpl implements TiketService {
    private TiketRepository tiketRepository;

    public TiketServiceImpl (TiketRepository tiketRepository) {
        this.tiketRepository = tiketRepository;
    }

    @Override
    public boolean create(Tiket tiket) {
        Tiket answer = null;
        try {
            answer = tiketRepository.save(tiket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer == null ? false : true;
    }

    @Override
    public List<Tiket> readAll() {
        return (List<Tiket>) tiketRepository.findAll();
    }

    public Object read(long id) {
        Tiket answer = null;
        try {
            answer = tiketRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer == null ? "Билет не найден. ID = " + id : answer;
    }

    @Override
    public boolean delete(long id) {
        try {
            tiketRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(long id, long days) {
        try {
            Tiket tiket = tiketRepository.findById(id).orElseThrow();
            long t_days = days*24*60*60*1000; //Дни в миллисекундах
            long t_new = tiket.getEndDate().getTime() + t_days;
            tiket.setEndDate(new Timestamp(t_new));
            tiketRepository.save(tiket);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
