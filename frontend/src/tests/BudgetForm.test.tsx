import { describe, test, expect, vi } from 'vitest';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import BudgetForm from '../components/BudgetForm';

// helper to avoid act() warnings on initial render/useEffect
const doRender = async (ui: React.ReactElement) => waitFor(() => render(ui));

describe('BudgetForm', () => {
    test('renders fields & button', async () => {
        await doRender(<BudgetForm />);
        expect(screen.getByRole('textbox',   { name: /description/i })).toBeInTheDocument();
        expect(screen.getByRole('spinbutton',{ name: /amount/i      })).toBeInTheDocument();
        expect(screen.getByRole('textbox',   { name: /category/i    })).toBeInTheDocument();
        expect(screen.getByLabelText(/date/i)).toBeInTheDocument();
        expect(screen.getByRole('button',    { name: /submit/i      })).toBeInTheDocument();
    });

    test('submits user input', async () => {
        globalThis.fetch = vi.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve({}),
            } as Response)
        ) as typeof fetch;


        const mockSubmit = vi.fn();
        await doRender(<BudgetForm onSubmit={mockSubmit} />);

        await userEvent.type(screen.getByRole('textbox', { name: /description/i }), 'Rent');
        await userEvent.type(screen.getByRole('spinbutton', { name: /amount/i }), '1200');
        await userEvent.type(screen.getByRole('textbox', { name: /category/i }), 'Housing');
        await userEvent.type(screen.getByLabelText(/date/i), '2024-04-25');
        await userEvent.click(screen.getByRole('button', { name: /submit/i }));

        await waitFor(() =>
            expect(mockSubmit).toHaveBeenCalledWith({
                description: 'Rent',
                amount: 1200,
                category: 'Housing',
                date: '2024-04-25',
            }),
        );
    });
});
