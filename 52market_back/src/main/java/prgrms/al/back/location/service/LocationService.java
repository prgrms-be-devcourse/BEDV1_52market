package prgrms.al.back.location.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.domain.NearAreaStepTwo;
import prgrms.al.back.location.repository.LocationRepository;
import prgrms.al.back.location.repository.NearAreaStepOneRepository;
import prgrms.al.back.location.repository.NearAreaStepTwoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final NearAreaStepOneRepository nearAreaStepOneRepository;
    private final NearAreaStepTwoRepository nearAreaStepTwoRepository;

    public Optional<Location> findByName(String name){
        return locationRepository.findByName(name);
    }

    public List<Location> findNearLocationStepOne(Location location){
        List<NearAreaStepOne> list_one = nearAreaStepOneRepository.findNearLocation(location);
        List<Location> list = new ArrayList<>();

        for(NearAreaStepOne one : list_one){
            list.add(one.getEnd());
        }
        return list;
    }

    public List<Location> findNearLocationStepTwo(Location location){
        List<NearAreaStepTwo> list_two = nearAreaStepTwoRepository.findNearLocation(location);
        List<Location> list = new ArrayList<>();

        for(NearAreaStepTwo two : list_two){
            list.add(two.getEnd());
        }
        return list;
    }
}
