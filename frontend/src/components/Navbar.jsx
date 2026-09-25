import { useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import './Navbar.css';

export default function Navbar() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const { pathname } = useLocation();
  const navigate = useNavigate();

  const token = localStorage.getItem('token');
  const isAuthenticated = Boolean(token);

  const handleLogout = () => {
    localStorage.removeItem('token');
    setIsMenuOpen(false);
    navigate('/login');
  };

  return (
    <nav className="nav">
      <div className="nav-left">
        {/* Hamburger Menu */}
        <div
          className="hamburger-menu"
          onClick={() => setIsMenuOpen(!isMenuOpen)}
          aria-label="Toggle menu"
        >
          <div className="hamburger-line"></div>
          <div className="hamburger-line"></div>
          <div className="hamburger-line"></div>
        </div>

        {/* Brand Title */}
        <Link to="/" className="nav-brand" onClick={() => setIsMenuOpen(false)}>
          Ask Lord Krishna 🕉️
        </Link>
      </div>

      {/* Dropdown Menu */}
      {isMenuOpen && (
        <div className="dropdown-menu">
          <ul>
            <li>
              <Link to="/" onClick={() => setIsMenuOpen(false)}>
                Home
              </Link>
            </li>
            {isAuthenticated && (
              <li>
                <Link to="/chat" onClick={() => setIsMenuOpen(false)}>
                  Ask Krishna
                </Link>
              </li>
            )}
            <li>
              <Link to="/about" onClick={() => setIsMenuOpen(false)}>
                About
              </Link>
            </li>
            <li>
              <Link to="/contact" onClick={() => setIsMenuOpen(false)}>
                Contact Us
              </Link>
            </li>
          </ul>
        </div>
      )}

      {/* Right side auth buttons */}
      <div className="auth-buttons">
        {isAuthenticated ? (
          <>
            <Link
              to="/chat"
              className={`auth-btn ${pathname === '/chat' ? 'active' : ''}`}
            >
              Ask Krishna
            </Link>
            <button
              type="button"
              onClick={handleLogout}
              className="auth-btn logout-btn"
            >
              Logout
            </button>
          </>
        ) : (
          <>
            <Link
              to="/login"
              className={`auth-btn ${pathname === '/login' ? 'active' : ''}`}
            >
              Login
            </Link>
            <Link
              to="/register"
              className={`auth-btn ${pathname === '/register' ? 'active' : ''}`}
            >
              Register
            </Link>
          </>
        )}
      </div>
    </nav>
  );
}
