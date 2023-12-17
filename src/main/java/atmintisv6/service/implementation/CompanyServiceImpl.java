/**
 * Implementation of the CompanyService interface providing functionality
 * related to managing companies.
 */
package atmintisv6.service.implementation;

import atmintisv6.dto.Company;
import atmintisv6.repository.CompanyRepository;
import atmintisv6.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    /**
     * Company repository to handle data access operations for companies.
     */
    private final CompanyRepository companyRepository;

    /**
     * Constructs an instance of CompanyServiceImpl with the specified CompanyRepository.
     *
     * @param companyRepository The repository for handling data access operations for companies.
     */
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Saves the provided company to the repository.
     *
     * @param newCompany The Company object to be saved.
     */
    public void save(Company newCompany) {
        companyRepository.save(newCompany);
    }
}
