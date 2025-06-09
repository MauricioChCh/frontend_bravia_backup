package com.example.bravia.data.mapper

        import com.example.bravia.data.remote.dto.CityDTO
        import com.example.bravia.data.remote.dto.CountryDTO
        import com.example.bravia.data.remote.dto.LocationDTO
        import com.example.bravia.domain.model.City
        import com.example.bravia.domain.model.Country
        import com.example.bravia.domain.model.Location
        import javax.inject.Inject

        class LocationMapper @Inject constructor(
            private val cityMapper: CityMapper,
            private val countryMapper: CountryMapper
        ) {
            fun mapToDomain(dto: LocationDTO): Location {
                return Location(
                    id = dto.id,
                    address = dto.address,
                    city = cityMapper.mapToDomain(dto.city),
                    country = countryMapper.mapToDomain(dto.country)
                )
            }

            fun mapToDto(domain: Location): LocationDTO {
                return LocationDTO(
                    id = domain.id,
                    address = domain.address,
                    city = cityMapper.mapToDto(domain.city),
                    country = countryMapper.mapToDto(domain.country)
                )
            }
        }

        class CityMapper @Inject constructor() {
            fun mapToDomain(dto: CityDTO): City {
                return City(
                    id = dto.id,
                    name = dto.name
                )
            }

            fun mapToDto(domain: City): CityDTO {
                return CityDTO(
                    id = domain.id,
                    name = domain.name
                )
            }
        }

        class CountryMapper @Inject constructor() {
            fun mapToDomain(dto: CountryDTO): Country {
                return Country(
                    id = dto.id,
                    name = dto.name
                )
            }

            fun mapToDto(domain: Country): CountryDTO {
                return CountryDTO(
                    id = domain.id,
                    name = domain.name
                )
            }
        }