export default function About() {
  return (
    <section className="about-page">
      <div className="about-container">

        {/* About This Website Section */}
        <div className="about-section">
          <h2>About This Website</h2>
          <p>
            This website is an interactive platform designed to provide spiritual guidance
            and wisdom based on the timeless teachings of Lord Krishna from the Bhagavad Gita.
            Users can ask philosophical and life-related questions and receive insightful
            responses powered by AI technology, making ancient wisdom accessible in the modern world.
          </p>
          <p className="note">
            <em>Note: This project is a personal implementation and realization of an idea by its creator. Its intention is not to hurt any religious centiments , it is just to guide people with his devine teachings</em>
          </p>
        </div>

        {/* About The Creator Section */}
        <div className="about-section">
          <h2>About The Creator</h2>
          <p>
            Vineet M , is a passionate full-stack developer and dedicated engineering student
            based in Bangalore, India. With expertise spanning frontend and backend technologies,
            he combines technical proficiency with creativity to build meaningful digital experiences
            that solve real-world problems.
          </p>
          <p>
            As an engineering student with a strong foundation in modern web development,
            he continuously explores new technologies and frameworks to create innovative solutions.
          </p>
          <div className="projects-section">
            <h3>Check out some other projects:</h3>
            <ul className="projects-list">
              <li>
                <a
                  href="https://boisterous-meringue-d6e80e.netlify.app/#service-0"
                  target="_blank"
                  rel="noopener noreferrer"
                  className="project-link"
                >
                  Portfolio Project - Services
                </a>
              </li>
              <li>
                <a
                  href="https://papaya-jelly-b6428a.netlify.app/"
                  target="_blank"
                  rel="noopener noreferrer"
                  className="project-link"
                >
                  Papaya Jelly Application
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
  );
}
